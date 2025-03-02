package com.example.movie_booking.service;

import com.example.movie_booking.dao.MovieDAO;
import com.example.movie_booking.dao.ShowtimeDAO;
import com.example.movie_booking.model.Movie;

import java.sql.SQLException;
import java.util.List;

public class MovieService {
    private final MovieDAO movieDAO =new MovieDAO();
    private final ShowtimeDAO showtimeDAO=new ShowtimeDAO();

    public int addMovie(Movie movie) {

        return movieDAO.insertMovie(movie);
    }



    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.fetchAllMovies();
    }
    //-------------------------------------------------------------------------

    public List<Movie> searchMoviesByName(String name) {
        return movieDAO.searchMoviesByName(name);
    }




    public Movie getMovieById(int movieId) {
        return movieDAO.getMovieById(movieId);
    }

    public boolean updateMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }


    //--------------get movies for home page active ---------

    public List<Movie> getAllActiveMovies() {
        return movieDAO.getMoviesByStatus("Active");
    }


    //----------------delete movie -------------------------------
    public boolean deleteMovieWithShowtimes(int movieId) {
        try {
            // First, delete related showtimes
            showtimeDAO.deleteShowtimesByMovieId(movieId);

            // Then, delete the movie
            return movieDAO.deleteMovie(movieId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
