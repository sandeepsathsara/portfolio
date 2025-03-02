package com.example.movie_booking.service;

import com.example.movie_booking.dao.ShowtimeDAO;
import com.example.movie_booking.model.Showtime;

import java.sql.SQLException;
import java.util.List;

public class ShowtimeService {

    private final ShowtimeDAO showtimeDAO = new ShowtimeDAO();

    public boolean addShowtime(Showtime showtime) {
        // Perform validation or other business logic if needed
        return showtimeDAO.insertShowtime(showtime);
    }



    public List<Showtime> getAllShows(int id){
        return showtimeDAO.fetchShowsByMovieId(id);
    }


    //----------------- delete show time show id ----------
    public boolean deleteShowtime(int showId) {
        try {
            return showtimeDAO.deleteShowtime(showId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
