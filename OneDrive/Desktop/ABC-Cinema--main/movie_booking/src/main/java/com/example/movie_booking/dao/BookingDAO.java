package com.example.movie_booking.dao;


import com.example.movie_booking.model.Booking;
import com.example.movie_booking.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class BookingDAO {

    public boolean addBooking(Booking booking) {
        String query = "INSERT INTO bookings (userId, movieName, date, time, seatNumbers, totalPrice, bookingId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, booking.getUserId());
            if (booking.getMovieName() != null) {
                stmt.setString(2, booking.getMovieName());
            } else {
                stmt.setNull(2, Types.VARCHAR); // Set null if movieName is not provided
                System.out.println("Warning: Movie name is null!");
            }
            stmt.setString(3, booking.getDate());
            stmt.setString(4, booking.getTime());
            stmt.setArray(5, conn.createArrayOf("TEXT", booking.getSeatNumbers().toArray()));
            stmt.setDouble(6, booking.getTotalPrice());
            stmt.setString(7, booking.getBookingId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                List<String> seatNumbers = Arrays.asList((String[]) rs.getArray("seatNumbers").getArray());
                Booking booking = new Booking(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("movieName"),
                        rs.getString("date"),
                        rs.getString("time"),
                        seatNumbers,
                        rs.getDouble("totalPrice"),
                        rs.getString("bookingId")
                );
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean cancelBooking(int userId, String bookingId) {
        String query = "DELETE FROM bookings WHERE userId = ? AND bookingId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, bookingId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sql error");
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
