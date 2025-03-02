package com.example.movie_booking.dao;
import com.example.movie_booking.model.VerificationCode;
import com.example.movie_booking.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class VerificationCodeDAO {

    // Add a new verification code
    public boolean addVerificationCode(int userId, String code, LocalDateTime expiresAt) {
        String query = "INSERT INTO VerificationCode (userId, code, createdAt, expiresAt, isUsed) VALUES (?, ?, NOW(), ?, false)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, code);
            statement.setTimestamp(3, Timestamp.valueOf(expiresAt));

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Mark a verification code as used
    public boolean markCodeAsUsed(int userId, String code) {
        String query = "UPDATE VerificationCode SET isUsed = true WHERE userId = ? AND code = ? AND isUsed = false";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, code);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Retrieve a verification code for a specific user and code value
    public Optional<VerificationCode> getVerificationCode(int userId, String code) {
        String query = "SELECT * FROM VerificationCode WHERE userId = ? AND code = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setString(2, code);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new VerificationCode(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getString("code"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime(),
                        resultSet.getTimestamp("expiresAt").toLocalDateTime(),
                        resultSet.getBoolean("isUsed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}

