package com.example.movie_booking.model;

import java.util.List;

public class Movie {

    private int id;
    private String title;
    private String description;
    private String language;
    private String ageRange;
    private String imageUrl;
    private double rating;
    private String trailarUrl;
    private double price;
    private String status;
    private List<String> genre;

    // Constructors
    public Movie() {}

    public Movie(int id, String title, String description, String language, String ageRange,
                 String imageUrl, double rating, String trailarUrl, double price, String status,
                 List<String> genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.language = language;
        this.ageRange = ageRange;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.trailarUrl = trailarUrl;
        this.price = price;
        this.status = status;
        this.genre = genre;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getAgeRange() { return ageRange; }
    public void setAgeRange(String ageRange) { this.ageRange = ageRange; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getTrailarUrl() { return trailarUrl; }
    public void setTrailarUrl(String trailarUrl) { this.trailarUrl = trailarUrl; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getGenre() { return genre; }
    public void setGenre(List<String> genre) { this.genre = genre; }

}
