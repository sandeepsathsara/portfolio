package com.example.movie_booking.dao;

import com.example.movie_booking.model.ContactMessage;
import com.example.movie_booking.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ContactUsDAO {

    public ContactUsDAO() {
        // This constructor is kept simple; all setup is done in DBConnection.
    }

    public void saveContactMessage(ContactMessage contactMessage) throws SQLException {
        String query = "INSERT INTO contactMessage (name, email, message) VALUES (?, ?, ?)";

        // Use try-with-resources to ensure proper closure of resources
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Preparing to save message from: " + contactMessage.getName());

            preparedStatement.setString(1, contactMessage.getName());
            preparedStatement.setString(2, contactMessage.getEmail());
            preparedStatement.setString(3, contactMessage.getMessage());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contact message saved successfully.");
            } else {
                throw new SQLException("Failed to insert contact message.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            throw new SQLException("Failed to save contact message: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to establish database connection.", e);
        }
    }

    public List<ContactMessage> getAllContactMessages() throws SQLException {
        List<ContactMessage> messages = new ArrayList<>();
        String query = "SELECT id, name, email, message FROM contactMessage ORDER BY id DESC";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                ContactMessage message = new ContactMessage();
                message.setId(resultSet.getInt("id"));
                message.setName(resultSet.getString("name"));
                message.setEmail(resultSet.getString("email"));
                message.setMessage(resultSet.getString("message"));
                messages.add(message);
            }
        }
        catch (SQLException e) {
            System.err.println("Database error while retrieving: " + e.getMessage());
            throw new SQLException("Failed to retrieve contact messages: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to establish database connection.", e);
        }
        return messages;
    }
}