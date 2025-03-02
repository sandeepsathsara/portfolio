package com.example.movie_booking.controller.admin;

import com.example.movie_booking.model.ContactMessage;
import com.example.movie_booking.service.ContactMessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/inquiries")
public class AdminContactUsController extends HttpServlet {
    private ContactMessageService contactMessageService;

    @Override
    public void init() throws ServletException {
        contactMessageService = new ContactMessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ContactMessage> contactMessages = contactMessageService.getAllContactMessages();
            request.setAttribute("contactMessages", contactMessages);

            request.getRequestDispatcher("/views/admin/inquiries.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error retrieving contact messages: " + e.getMessage());
            request.getRequestDispatcher("/views/admin/inquiries.jsp").forward(request, response);

        }
    }
}