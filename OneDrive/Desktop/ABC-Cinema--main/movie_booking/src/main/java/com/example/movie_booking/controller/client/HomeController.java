package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.service.MovieService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", urlPatterns = {"/home","/"})
public class HomeController extends HttpServlet {

    MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Movie> activeMovies = movieService.getAllActiveMovies();

        // Set the movies as a request attribute
        System.out.println(activeMovies.isEmpty());

        request.setAttribute("movies", activeMovies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/homepage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Usually, doPost would handle form submissions, but it's not used here
        doGet(request, response);
    }
}

