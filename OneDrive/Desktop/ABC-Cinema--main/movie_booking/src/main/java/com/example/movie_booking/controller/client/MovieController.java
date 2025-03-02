package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.service.MovieService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/movies")
public class MovieController extends HttpServlet {
    private MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> activeMovies = movieService.getAllActiveMovies();
        request.setAttribute("movies", activeMovies);
        request.getRequestDispatcher("/views/client/all-movies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieName = request.getParameter("movie");
        List<Movie> foundMovies = movieService.searchMoviesByName(movieName);

        request.setAttribute("movies", foundMovies);
        request.getRequestDispatcher("/views/client/search" +
                "-movies.jsp").forward(request, response);
    }
}
