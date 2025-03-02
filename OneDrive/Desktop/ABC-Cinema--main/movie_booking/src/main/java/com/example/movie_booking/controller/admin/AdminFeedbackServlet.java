package com.example.movie_booking.controller.admin;

import com.example.movie_booking.model.Feedback;
import com.example.movie_booking.service.FeedbackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/admin/feedbacks")
public class AdminFeedbackServlet extends HttpServlet {
    private FeedbackService feedbackService;

    @Override
    public void init() {
        try {
            feedbackService = new FeedbackService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch all feedbacks from the database
            List<Feedback> feedbackList = feedbackService.getAllFeedback();

            // Set the feedback list as an attribute for the JSP page
            request.setAttribute("feedbackList", feedbackList);
            request.getRequestDispatcher("/views/admin/feedback.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error fetching feedbacks: " + e.getMessage());
            request.getRequestDispatcher("/views/admin/feedback.jsp").forward(request, response);
        }
    }
}