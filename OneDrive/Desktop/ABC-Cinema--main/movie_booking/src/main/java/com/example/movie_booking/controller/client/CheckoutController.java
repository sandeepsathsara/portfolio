package com.example.movie_booking.controller.client;

import com.example.movie_booking.config.AppConfig;
import com.example.movie_booking.model.User;
import com.example.movie_booking.service.BookingService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.api.payments.Amount;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/make-checkout", "/checkout-method"})
public class CheckoutController extends HttpServlet {
    private BookingService bookingService;
    private static final String PAYPAL_CLIENT_ID = AppConfig.CLIENT_ID;
    private static final String PAYPAL_CLIENT_SECRET = AppConfig.SECURITY_ID;
    private static final String MODE = "sandbox";

    @Override
    public void init() {
        bookingService = new BookingService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action received: " + action); // Debugging output

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        switch (action) {
            case "make-checkout":
                processMakeCheckout(request, response);
                break;
            case "checkout-method":
                processCheckoutMethod(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }

    private void processMakeCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("No user in session, redirecting to login"); // Debugging output
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        try {

            int movieId = Integer.parseInt(request.getParameter("movieId"));
            String showId = request.getParameter("showId");
            String showDate = request.getParameter("showDate");
            String showTime = request.getParameter("showTime");
            String selectedSeats = request.getParameter("selectedSeats");
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            String movieName = request.getParameter("movieName");

            Map<String, Object> bookingDetails = new HashMap<>();
            bookingDetails.put("movieId", movieId);
            bookingDetails.put("showId", showId);
            bookingDetails.put("showDate", showDate);
            bookingDetails.put("showTime", showTime);
            bookingDetails.put("selectedSeats", selectedSeats);
            bookingDetails.put("totalPrice", totalPrice);

            // Add booking details to the request scope
            request.setAttribute("bookingDetails", bookingDetails);


            if (bookingDetails == null) {
                System.out.println("No booking details found, redirecting to movie selection"); // Debugging output
                response.sendRedirect(request.getContextPath() + "/select-movie");
                return;
            }

            // Debugging output of booking details
            System.out.println("Booking Details: " + bookingDetails);

            request.setAttribute("bookingDetails", bookingDetails);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/checkout.jsp");
            dispatcher.forward(request, response);

        } finally {

        }
    }


    private void processCheckoutMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Map<String, Object> bookingDetails = (Map<String, Object>) session.getAttribute("bookingDetails");

        if (bookingDetails == null) {
            System.out.println("No booking details found, redirecting to movie selection");
            response.sendRedirect(request.getContextPath() + "/select-movie");
            return;
        }

        String totalPriceStr = request.getParameter("total"); // This is the price in LKR
        double totalPriceLKR = 0.0;
        try {
            totalPriceLKR = Double.parseDouble(totalPriceStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid totalPrice value: " + totalPriceStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid total price in booking details.");
            return;
        }

// Convert LKR to USD, e.g. 1 USD = 320 LKR
        double exchangeRate = 1.0 / 320.0;
        double totalPriceUSD = totalPriceLKR * exchangeRate;

        System.out.println("LKR Price: " + totalPriceLKR + " => USD Price: " + totalPriceUSD);


        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        try {
            APIContext apiContext = new APIContext(PAYPAL_CLIENT_ID, PAYPAL_CLIENT_SECRET, MODE);

            // Create PayPal Payment
            Payment payment = createPayPalPayment(
                    apiContext,
                    request.getContextPath(),
                    request.getServerName(),
                    request.getServerPort(),
                    request.getScheme(),
                    totalPriceUSD,
                    bookingDetails.getOrDefault("movieName", "Movie Ticket").toString(),
                    user.getFullName()
            );

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    bookingDetails.put("paypalPaymentId", payment.getId());
                    session.setAttribute("bookingDetails", bookingDetails);
                    response.sendRedirect(links.getHref());
                    return;
                }
            }

        } catch (PayPalRESTException e) {
            System.out.println("PayPal processing failed: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "PayPal processing failed: " + e.getMessage());
        }
    }

    private Payment createPayPalPayment(
            APIContext apiContext,
            String contextPath,
            String serverName,
            int serverPort,
            String scheme,
            double totalAmount,
            String movieName,
            String customerName) throws PayPalRESTException {


        Amount amount = new Amount();  // Note the capital 'A'
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", totalAmount));

        Transaction transaction = new Transaction();
        transaction.setDescription("Booking for " + customerName);
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(String.format("%s://%s:%d%s/booking-success",
                scheme, serverName, serverPort, contextPath));
        redirectUrls.setCancelUrl(String.format("%s://%s:%d%s/booking-cancel",
                scheme, serverName, serverPort, contextPath));
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }
}