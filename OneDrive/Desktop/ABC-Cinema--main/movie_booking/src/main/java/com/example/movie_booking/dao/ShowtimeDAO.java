package com.example.movie_booking.dao;

import com.example.movie_booking.model.Showtime;
import com.example.movie_booking.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeDAO {

    public boolean insertShowtime(Showtime showtime) {
        String sql = "INSERT INTO showtime (movie_id, date, time, theater) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, showtime.getMovieId());
            stmt.setDate(2, Date.valueOf((showtime.getDate())));
            stmt.setString(3, showtime.getTime());
            stmt.setString(4, showtime.getTheater());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }





    public List<Showtime> fetchShowsByMovieId(int id){

        List<Showtime> showtimes = new ArrayList<>();
        String query = "SELECT * FROM showtime WHERE movie_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Showtime showtime = new Showtime();
                showtime.setId(resultSet.getInt("id"));
                showtime.setMovieId(resultSet.getInt("movie_id"));
                showtime.setDate(resultSet.getDate("date").toLocalDate());
                showtime.setTime(resultSet.getString("time"));
                showtime.setTheater(resultSet.getString("theater"));
                showtimes.add(showtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return showtimes;
    }



    // ----------------------------- Delete Shows by show id -----------------------------
    public boolean deleteShowtimesByMovieId(int movieId) throws SQLException {
        String deleteQuery = "DELETE FROM showtime WHERE movie_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            stmt.setInt(1, movieId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------Delete Show time --------------------
    public boolean deleteShowtime(int showId) throws SQLException {
        String deleteQuery = "DELETE FROM showtime WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            stmt.setInt(1, showId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
