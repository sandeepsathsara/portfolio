package com.example.movie_booking.service;

import com.example.movie_booking.dao.FeedbackDAO;
import com.example.movie_booking.model.Feedback;

import java.util.List;

public class FeedbackService {
    private final FeedbackDAO feedbackDAO;

    public FeedbackService() throws Exception {
        this.feedbackDAO = new FeedbackDAO();
    }

    // Submit feedback after validation and calculations
    public void submitFeedback(Feedback feedback) throws Exception {
        try {
            validateFeedback(feedback);
            calculateAverageRating(feedback);
            feedbackDAO.addFeedback(feedback);
        } catch (Exception e) {
            throw new Exception("Error submitting feedback: " + e.getMessage(), e);
        }
    }

    // Fetch all feedback records
    public List<Feedback> getAllFeedback() throws Exception {
        return feedbackDAO.getAllFeedback();
    }

    // Validate feedback fields
    private void validateFeedback(Feedback feedback) throws Exception {
        if (feedback.getName() == null || feedback.getName().trim().isEmpty()) {
            throw new Exception("Name is required.");
        }
        if (feedback.getEmail() == null || !feedback.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("Invalid email format.");
        }
        if (feedback.getSuggestions() == null || feedback.getSuggestions().trim().isEmpty()) {
            throw new Exception("Suggestions are required.");
        }

        validateRating("Experience", feedback.getExperience());
        validateRating("TicketEase", feedback.getTicketEase());
        validateRating("Payments", feedback.getPayments());
        validateRating("Cleanliness", feedback.getCleanliness());
        validateRating("Quality", feedback.getQuality());
        validateRating("Staff", feedback.getStaff());
    }

    // Validate a single rating field
    private void validateRating(String fieldName, int rating) throws Exception {
        if (rating <= 0 || rating > 5) {
            throw new Exception(fieldName + " rating must be between 1 and 5.");
        }
    }

    // Calculate the average rating and set it in the feedback object
    private void calculateAverageRating(Feedback feedback) {
        int sum = feedback.getExperience()
                + feedback.getTicketEase()
                + feedback.getPayments()
                + feedback.getCleanliness()
                + feedback.getQuality()
                + feedback.getStaff();

        int count = 6; // Total number of rating fields
        feedback.setTotalRate(sum / count); // Set average rating
    }
}
