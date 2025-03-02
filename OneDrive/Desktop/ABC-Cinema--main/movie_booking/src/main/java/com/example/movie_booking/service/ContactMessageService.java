package com.example.movie_booking.service;

import com.example.movie_booking.dao.ContactUsDAO;
import com.example.movie_booking.model.ContactMessage;

import java.sql.SQLException;
import java.util.List;

public class ContactMessageService {

    private ContactUsDAO contactMessageDAO;

    public ContactMessageService() {
        this.contactMessageDAO = new ContactUsDAO();
    }

    public boolean saveContactMessage(ContactMessage contactMessage) throws SQLException {

            validateContactMessage(contactMessage);
            this.contactMessageDAO.saveContactMessage(contactMessage);
            return true;

    }

    private void validateContactMessage(ContactMessage message) {
        if (message == null) {
            throw new IllegalArgumentException("Contact message cannot be null");
        }
        if (message.getName() == null || message.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (message.getEmail() == null || message.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
            throw new IllegalArgumentException("Message is required");
        }
    }

    public List<ContactMessage> getAllContactMessages() throws SQLException {
        return contactMessageDAO.getAllContactMessages();
    }
}