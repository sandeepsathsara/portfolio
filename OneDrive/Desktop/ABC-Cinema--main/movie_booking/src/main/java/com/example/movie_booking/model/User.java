package com.example.movie_booking.model;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private int mobile;
    private String role;
    private boolean isActive;
    private boolean emailVerified;

    // Constructor
    public User(int id, String fullName, String email, String password, int mobile, String role,boolean isActive, boolean emailVerified) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
        this.isActive = isActive;
        this.emailVerified = emailVerified;
    }

    public User(int id, String fullName, String email, int mobile, String role, boolean isActive, boolean emailVerified) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
        this.isActive = isActive;
        this.emailVerified = emailVerified;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    // Additional Methods
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                ", emailVerified=" + emailVerified +
                '}';
    }
}
