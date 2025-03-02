package com.example.movie_booking.dao;

import com.example.movie_booking.model.Feedback;
import com.example.movie_booking.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    private final Connection connection;

    public FeedbackDAO() throws Exception {
        connection = DBConnection.getConnection();
    }

    // Add feedback to the database
    public void addFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO feedback (name, email, experience_rating, ticket_ease_rating, " +
                "payment_rating, cleanliness_rating, quality_rating, staff_rating, " +
                "suggestions, total_rate, date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, feedback.getName());
            ps.setString(2, feedback.getEmail());
            ps.setInt(3, feedback.getExperience());
            ps.setInt(4, feedback.getTicketEase());
            ps.setInt(5, feedback.getPayments());
            ps.setInt(6, feedback.getCleanliness());
            ps.setInt(7, feedback.getQuality());
            ps.setInt(8, feedback.getStaff());
            ps.setString(9, feedback.getSuggestions());
            ps.setInt(10, feedback.getTotalRate());
            ps.setTimestamp(11, feedback.getSubmissionDate());

            System.out.println("Executing SQL: " + sql);
            ps.executeUpdate();
        }
    }

    // Retrieve all feedback from the database
    public List<Feedback> getAllFeedback() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY date DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setName(rs.getString("name"));
                feedback.setEmail(rs.getString("email"));
                feedback.setExperience(rs.getInt("experience_rating"));
                feedback.setTicketEase(rs.getInt("ticket_ease_rating"));
                feedback.setPayments(rs.getInt("payment_rating"));
                feedback.setCleanliness(rs.getInt("cleanliness_rating"));
                feedback.setQuality(rs.getInt("quality_rating"));
                feedback.setStaff(rs.getInt("staff_rating"));
                feedback.setSuggestions(rs.getString("suggestions"));
                feedback.setTotalRate(rs.getInt("total_rate"));
                feedback.setSubmissionDate(rs.getTimestamp("date"));

                feedbackList.add(feedback);
            }
        }
        return feedbackList;
    }
}
