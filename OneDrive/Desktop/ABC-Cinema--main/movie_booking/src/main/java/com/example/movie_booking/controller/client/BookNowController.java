package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.model.Showtime;
import com.example.movie_booking.service.MovieService;
import com.example.movie_booking.service.ShowtimeService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/book-now")
public class BookNowController extends HttpServlet {
    private final MovieService movieService = new MovieService();
    private final ShowtimeService showtimeService = new ShowtimeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the movie ID from the request parameter
        String movieIdParam = request.getParameter("id");
        if (movieIdParam == null || movieIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie ID is required");
            return;
        }

        try {
            int movieId = Integer.parseInt(movieIdParam);

            // Fetch the movie details by ID
            Movie movie = movieService.getMovieById(movieId);
            if (movie == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                return;
            }

            // Fetch all showtimes associated with this movie ID
            List<Showtime> showtimes = showtimeService.getAllShows(movieId);

            // Set movie and showtimes as request attributes
            request.setAttribute("movie", movie);
            request.setAttribute("showtimes", showtimes);

            // Forward to the seatbooking.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/seatbooking.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
