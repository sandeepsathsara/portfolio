package com.example.movie_booking.model;

import java.sql.Timestamp;

public class Feedback {

    private int id;
    private String name;
    private String email;
    private int experience;
    private int ticketEase;
    private int payments;
    private int cleanliness;
    private int quality;
    private int staff;
    private int totalRate;
    private String suggestions;
    private Timestamp submissionDate;

    // Default Constructor
    public Feedback() {
    }

    // Constructor with all fields
    public Feedback(int id, String name, String email, int experience, int ticketEase, int payments,
                    int cleanliness, int quality, int staff, int totalRate, String suggestions, Timestamp submissionDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.experience = experience;
        this.ticketEase = ticketEase;
        this.payments = payments;
        this.cleanliness = cleanliness;
        this.quality = quality;
        this.staff = staff;
        this.totalRate = totalRate;
        this.suggestions = suggestions;
        this.submissionDate = submissionDate;
    }

    // Constructor without ID (useful for creating new feedback)
    public Feedback(String name, String email, int experience, int ticketEase, int payments,
                    int cleanliness, int quality, int staff, int totalRate, String suggestions, Timestamp submissionDate) {
        this.name = name;
        this.email = email;
        this.experience = experience;
        this.ticketEase = ticketEase;
        this.payments = payments;
        this.cleanliness = cleanliness;
        this.quality = quality;
        this.staff = staff;
        this.totalRate = totalRate;
        this.suggestions = suggestions;
        this.submissionDate = submissionDate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getTicketEase() {
        return ticketEase;
    }

    public void setTicketEase(int ticketEase) {
        this.ticketEase = ticketEase;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }
}
