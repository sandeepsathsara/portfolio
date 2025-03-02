package com.example.movie_booking.model;

import java.time.LocalDate;
import java.util.Date;

public class Showtime {
    private int id;
    private int movieId;
    private LocalDate date;
    private String time;
    private String theater;

    // Constructors
    public Showtime() {}

    public Showtime(int id, int movieId, LocalDate  date, String time, String theater) {
        this.id = id;
        this.movieId = movieId;
        this.date = date;
        this.time = time;
        this.theater = theater;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate  date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getTheater() { return theater; }
    public void setTheater(String theater) { this.theater = theater; }
}
