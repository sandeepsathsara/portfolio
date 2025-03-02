package com.example.movie_booking.controller.client;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/seat-Booking")
public class SeatBookingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            int movieId = Integer.parseInt(request.getParameter("movieId"));
            String showId = request.getParameter("showId");
            String showDate = request.getParameter("showDate");
            String showTime = request.getParameter("showTime");
            String selectedSeats = request.getParameter("selectedSeats");
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
            String movieName = request.getParameter("movieName");

            System.out.println(showTime);
            System.out.println(selectedSeats);
            System.out.println(movieName);

            Map<String, Object> bookingDetails = new HashMap<>();
            bookingDetails.put("movieId", movieId);
            bookingDetails.put("showId", showId);
            bookingDetails.put("showDate", showDate);
            bookingDetails.put("showTime", showTime);
            bookingDetails.put("selectedSeats", selectedSeats);
            bookingDetails.put("totalPrice", totalPrice);

            // Add booking details to the request scope
            request.setAttribute("bookingDetails", bookingDetails);
            // Redirect to the checkout page
              RequestDispatcher dispatcher = request.getRequestDispatcher("/");
              dispatcher.forward(request, response);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}