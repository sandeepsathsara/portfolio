package com.example.movie_booking.service;

import com.example.movie_booking.dao.BookingDAO;
import com.example.movie_booking.model.Booking;

import java.util.List;

public class BookingService {

    private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    /**
     * Attempts to create a booking and processes payment if needed.
     * @param booking the booking details to be added.
     * @return true if booking is successfully created and payment (if applicable) is processed.
     */
    public boolean createBooking(Booking booking) {
        // Simulate payment processing logic, if necessary
        boolean paymentProcessed = true; // Replace this with actual payment processing logic

        if (paymentProcessed) {
            return bookingDAO.addBooking(booking);
        } else {
            System.out.println("Payment failed, booking not created.");
            return false;
        }
    }

    /**
     * Cancels a booking for a given user ID and booking ID.
     * @param userId the ID of the user.
     * @param bookingId the ID of the booking to cancel.
     * @return true if the booking was successfully canceled.
     */
    public boolean cancelBooking(int userId, String bookingId) {
        return bookingDAO.cancelBooking(userId, bookingId);
    }

    /**
     * Retrieves all bookings from the database.
     * @return a list of bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}
