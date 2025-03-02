package com.example.movie_booking.model;

import java.util.List;

public class Booking {
    private int id;
    private int userId;
    private String movieName;
    private String date;
    private String time;
    private List<String> seatNumbers;
    private double totalPrice;
    private String bookingId;  // New field

    public Booking(int id, int userId, String movieName, String date, String time, List<String> seatNumbers, double totalPrice, String bookingId) {
        this.id = id;
        this.userId = userId;
        this.movieName = movieName;
        this.date = date;
        this.time = time;
        this.seatNumbers = seatNumbers;
        this.totalPrice = totalPrice;
        this.bookingId = bookingId;  // Initialize the new field
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public List<String> getSeatNumbers() { return seatNumbers; }
    public void setSeatNumbers(List<String> seatNumbers) { this.seatNumbers = seatNumbers; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
}
