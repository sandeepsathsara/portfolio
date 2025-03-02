package com.example.movie_booking.controller.client;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.service.MovieService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MovieDetails", value = "/moviedetails")
public class MovieDetailsController extends HttpServlet {
    MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


           try {

               int id = Integer.parseInt(request.getParameter("movieId"));
               System.out.println(id);


               // Fetch the movie details from the service
               List<Movie> movies = movieService.getAllActiveMovies();
               Movie movie = movieService.getMovieById(id);

               if (movie == null) {
                   response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
                   return;
               }

               // Set the movie as a request attribute
               request.setAttribute("movie", movie);
               request.setAttribute("movies", movies);

               // Forward the request to the JSP page
               request.getRequestDispatcher("/views/client/movie-details.jsp").forward(request, response);


           } catch (NumberFormatException e) {
               response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
           } catch (Exception e) {
               throw new RuntimeException(e);
           }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
