package com.example.movie_booking.dao;

import com.example.movie_booking.model.User;
import com.example.movie_booking.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all database operations related to user authentication
 * including checking for existing email, user authentication, user registration,
 * and updating user details.
 */
public class UserDao {

    // Checks if an email already exists in the database
    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // If count > 0, email exists
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error during email check.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int addUser(String fullName, String email, int mobile, String password, String role, boolean isActive, boolean emailVerified) {
        String query = "INSERT INTO users (fullName, email, password, mobile, role, isActive, emailVerified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setInt(4, mobile);
            statement.setString(5, role);
            statement.setBoolean(6, isActive);
            statement.setBoolean(7, emailVerified);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            throw new RuntimeException("Database error during user registration.", e);
        } catch (Exception e) {
            throw new RuntimeException("System error during user registration.", e);
        }
        return -1;
    }

    // Authenticates a user
    public User authenticateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (!resultSet.getBoolean("isActive") || !resultSet.getBoolean("emailVerified")) {
                    return null; // User is not active or not verified
                }
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("mobile"),
                        resultSet.getString("role"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getBoolean("emailVerified")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error during user authentication.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Activates the user's account
    public boolean activateUser(int userId) {
        String query = "UPDATE users SET emailVerified = true, isActive = true WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error during user activation.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Updates the user's password
    public boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE Users SET password = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error during password update.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Retrieves a user ID by their email
    public int getUserIdByEmail(String email) {
        String query = "SELECT id FROM Users WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while retrieving user ID.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1; // User not found
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email"),
                        resultSet.getInt("mobile"),
                        resultSet.getString("role"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getBoolean("emailVerified")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching all users.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean setActiveStatus(int userId, boolean isActive) {
        String query = "UPDATE Users SET isActive = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, isActive);
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while updating user active status.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

