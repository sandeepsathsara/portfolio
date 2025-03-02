package com.example.movie_booking.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    // This method ensures a new connection is created every time it's called
    public static Connection getConnection() throws Exception {
        // Load the database properties
        Properties properties = new Properties();
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new Exception("Unable to find db.properties");
            }
            properties.load(input);
        }

        // Retrieve properties
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        // Initialize and return the connection
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
