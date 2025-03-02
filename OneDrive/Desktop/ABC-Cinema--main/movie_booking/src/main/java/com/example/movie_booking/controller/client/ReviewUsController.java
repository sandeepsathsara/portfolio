package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.Feedback;
import com.example.movie_booking.service.FeedbackService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/review-us")
public class ReviewUsController extends HttpServlet {
    private final FeedbackService feedbackService = new FeedbackService();

    public ReviewUsController() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the review-us.jsp page where the review form is presented
        request.getRequestDispatcher("/views/client/feedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Log all incoming parameters
            System.out.println("Received parameters:");
            request.getParameterMap().forEach((key, value) -> {
                System.out.println(key + " = " + String.join(", ", value));
            });

            // Create a new Feedback object
            Feedback feedback = new Feedback();

            // Set basic information
            feedback.setName(sanitizeInput(request.getParameter("name")));
            feedback.setEmail(sanitizeInput(request.getParameter("email")));
            feedback.setSuggestions(sanitizeInput(request.getParameter("suggestions")));

            // Parse ratings
            int experience = parseSingleRating(request.getParameter("experience"));
            int ticketEase = parseSingleRating(request.getParameter("ticketEase"));
            int payments = parseSingleRating(request.getParameter("payments"));
            int cleanliness = parseSingleRating(request.getParameter("cleanliness"));
            int quality = parseSingleRating(request.getParameter("quality"));
            int staff = parseSingleRating(request.getParameter("staff"));

            feedback.setExperience(experience);
            feedback.setTicketEase(ticketEase);
            feedback.setPayments(payments);
            feedback.setCleanliness(cleanliness);
            feedback.setQuality(quality);
            feedback.setStaff(staff);

            // Calculate total rate (average of all ratings)
            int totalRate = calculateTotalRate(experience, ticketEase, payments, cleanliness, quality, staff);
            feedback.setTotalRate(totalRate);

            // Add submission timestamp
            feedback.setSubmissionDate(new Timestamp(System.currentTimeMillis()));

            // Log feedback details
            System.out.println("Feedback: " + feedback);

            // Submit feedback through service
            feedbackService.submitFeedback(feedback);

            // Redirect with success message
            HttpSession session = request.getSession();
            session.setAttribute("message", "Thank you for your feedback!");
            request.getRequestDispatcher("/views/client/feedback.jsp").forward(request, response);
        } catch (Exception e) {
            handleError(request, response, "Error processing feedback: " + e.getMessage());
        }
    }

    // Parse single rating from request
    private int parseSingleRating(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Default to 0 if parsing fails
        }
    }

    // Calculate the total rate (average of all ratings)
    private int calculateTotalRate(int... ratings) {
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return ratings.length > 0 ? sum / ratings.length : 0;
    }

    // Sanitize input to remove unwanted characters
    private String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("<[^>]*>", "") // Remove HTML tags
                .replaceAll("[^a-zA-Z0-9\\s@._-]", "") // Remove special characters
                .trim();
    }

    // Handle errors by forwarding to error page
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        System.err.println("Error: " + errorMessage);
        request.setAttribute("error", "Cannot submit feedback");
        request.getRequestDispatcher("/views/client/feedback.jsp").forward(request, response);
    }
}
