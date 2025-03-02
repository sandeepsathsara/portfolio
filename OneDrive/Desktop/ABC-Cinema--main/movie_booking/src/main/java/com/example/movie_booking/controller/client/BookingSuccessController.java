package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.Booking;
import com.example.movie_booking.model.Movie;
import com.example.movie_booking.model.User;
import com.example.movie_booking.service.BookingService;
import com.example.movie_booking.service.EmailService;
import com.example.movie_booking.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/booking-success")
public class BookingSuccessController extends HttpServlet {
    private final BookingService bookingService = new BookingService();
    private final EmailService emailService = new EmailService();
    private final MovieService movieService= new MovieService();

    @Override
    public void init() {
        // Initialization here (e.g., setting API keys)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/auth/login");
            return;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> bookingDetails = (Map<String, Object>) session.getAttribute("bookingDetails");
        if (bookingDetails == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Booking details not found in session.");
            System.out.println("Booking details not in booking success controller.");
            return;
        }

        int movieId = Integer.parseInt(bookingDetails.get("movieId").toString());
        Movie movie =movieService.getMovieById(movieId);
        String movieTitle = movie.getTitle();

        String showDate = (String) bookingDetails.get("showDate");
        String showTime = (String) bookingDetails.get("showTime");
        double totalPrice = (Double) bookingDetails.get("totalPrice");

        String selectedSeats = (String) bookingDetails.get("selectedSeats");

        System.out.println("Booking Details success:");
        System.out.println("Movie ID: " + bookingDetails.get("movieId"));
        System.out.println("Show Date: " + bookingDetails.get("showDate"));
        System.out.println("Show Time: " + bookingDetails.get("showTime"));
        System.out.println("Selected Seats: " + bookingDetails.get("selectedSeats"));
        System.out.println("Total Price: " + bookingDetails.get("totalPrice"));

        if (selectedSeats == null || selectedSeats.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Seat numbers are missing.");
            return;
        }

// Split the selectedSeats by commas and trim whitespace
        List<String> seatNumbers = Arrays.stream(selectedSeats.split(","))
                .map(String::trim) // Trim whitespace
                .collect(Collectors.toList()); // Collect as list of strings

        String bookingId="";
        try {
            bookingId = generateBookingId(movieTitle, user.getId());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating booking ID: ", e);
        }

// Assuming Booking constructor can accept List<String> for seatNumbers
        Booking booking = new Booking(0, user.getId(), movieTitle, showDate, showTime, seatNumbers, totalPrice+20, bookingId);

// Proceed with booking logic

        try {
            if (bookingService.createBooking(booking)) {
                emailService.sendBookingConfirmationEmail(user.getEmail(), booking);
                response.sendRedirect(request.getContextPath() + "/views/client/booking-success.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create booking.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing booking: ", e);
        }
    }

    public String generateBookingId(String movieName, int userId) throws NoSuchAlgorithmException {
        try {
            // Generate a unique code using UUID
            String uniqueCode = generateUniqueCode(movieName + userId);

            // Create the booking ID in the desired format
            return movieName + userId + uniqueCode;
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate booking ID", e);
        }
    }

    private String generateUniqueCode(String input) throws NoSuchAlgorithmException {
        // Hash the input using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());

        // Convert first 6 bytes of the hash to a readable alphanumeric string
        StringBuilder uniqueCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            uniqueCode.append(Integer.toHexString((hashBytes[i] & 0xFF)));
        }

        return uniqueCode.toString().toUpperCase(); // Convert to uppercase for better readability
    }

}
