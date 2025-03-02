package com.example.movie_booking.controller.client;
import com.example.movie_booking.model.User;
import com.example.movie_booking.service.BookingService;
import com.example.movie_booking.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cancel-booking")
public class CancelBookingController extends HttpServlet {
    private BookingService bookingService = new BookingService();
    private EmailService emailService = new EmailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the cancellation form
        request.getRequestDispatcher("/views/client/cancel-booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login"); // Redirect to login if user not found in session
            return;
        }

        String email = user.getEmail(); // Use email from the user object directly
        int userId = user.getId(); // Use user ID from the user object directly
        String bookingIdStr = request.getParameter("bookingId");

        System.out.println(bookingIdStr+email);


        // Attempt to cancel the booking
        boolean isCancelled = bookingService.cancelBooking(userId, bookingIdStr);
        if (isCancelled) {
            try {
                emailService.sendBookingCancellationEmail(email); // Send email confirmation
                response.sendRedirect(request.getContextPath() + "/views/client/booking-cancel-success.jsp"); // Redirect on successful cancellation
            } catch (Exception e) {
                throw new RuntimeException("Error sending cancellation email", e);
            }
        } else {
            request.setAttribute("error", "Failed to cancel booking.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
