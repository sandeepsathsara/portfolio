package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.ContactMessage;
import com.example.movie_booking.service.ContactMessageService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/contact-us")
public class ContactUsController extends HttpServlet {
    private ContactMessageService contactMessageService;

    @Override
    public void init() {
        this.contactMessageService = new ContactMessageService();
    }

    // Display the contact form using doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the contact-us.jsp page
        request.getRequestDispatcher("/views/client/contact-us.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = validateInput(request.getParameter("name"), "Name");
            String email = validateInput(request.getParameter("email"), "Email");
            String message = validateInput(request.getParameter("message"), "Message");

            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email format");
            }

            ContactMessage contactMessage = new ContactMessage();
            contactMessage.setName(name);
            contactMessage.setEmail(email);
            contactMessage.setMessage(message);

            // Save the message and set appropriate feedback
            if (contactMessageService.saveContactMessage(contactMessage)) {
                request.setAttribute("success", "Thank you for contacting us! We will get back to you shortly.");
            } else {
                request.setAttribute("error", "An error occurred while saving your message. Please try again later.");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
        } catch (Exception e) {
            request.setAttribute("error", "A server error occurred. Please try again later.");
        }

        // Forward back to the contact-us.jsp page
        request.getRequestDispatcher("/views/client/contact-us.jsp").forward(request, response);
    }

    private String validateInput(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return input.trim();
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
