package com.example.movie_booking.controller.admin;


import com.example.movie_booking.model.Booking;
import com.example.movie_booking.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/movie-bookings",

})
public class BookingManagementController extends HttpServlet {
    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            request.setAttribute("message", "No bookings found.");
        } else {
            request.setAttribute("bookings", bookings);
        }
        request.getRequestDispatcher("/views/admin/reservation.jsp").forward(request, response);
    }
}
