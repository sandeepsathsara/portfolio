package com.example.movie_booking.dao;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class MovieDAO {

    public int insertMovie(Movie movie) {
        String sql = "INSERT INTO movie (title, description, language, age_range, image_url, rating, trailar_url, price, status, genre) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getLanguage());
            stmt.setString(4, movie.getAgeRange());
            stmt.setString(5, movie.getImageUrl());
            stmt.setDouble(6, movie.getRating());
            stmt.setString(7, movie.getTrailarUrl());
            stmt.setDouble(8, movie.getPrice());
            stmt.setString(9, movie.getStatus());

            // Convert genre list to PostgreSQL array format
            String[] genreArray = movie.getGenre().toArray(new String[0]);
            stmt.setArray(10, conn.createArrayOf("text", genreArray)); // Bind as PostgreSQL text[]

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if insertion failed
    }

    //----------------------------------------------------------------------------------------------

    public List<Movie> searchMoviesByName(String name) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie WHERE LOWER(title) LIKE ?";  // Ensure your table and column names are correct

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name.toLowerCase() + "%");  // Set the parameter before executing the query
            ResultSet rs = stmt.executeQuery();  // Execute the query after setting the parameter

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));

                // Convert SQL Array to List<String> if 'genre' is stored as an array in the database
                Array genreArray = rs.getArray("genre");
                if (genreArray != null) {
                    String[] genreStrings = (String[]) genreArray.getArray();
                    movie.setGenre(Arrays.asList(genreStrings));
                }

                movie.setLanguage(rs.getString("language"));
                movie.setStatus(rs.getString("status"));
                movie.setImageUrl(rs.getString("image_url"));
                movies.add(movie);
            }
            rs.close();  // Close ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);  // Handle other unexpected exceptions
        }
        return movies;
    }




    //-----------------------------------------------------------------------------------------------

    public List<Movie> fetchAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, title, genre, language, status, image_url FROM movie";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                // Convert SQL Array to List<String>
                Array genreArray = rs.getArray("genre");
                if (genreArray != null) {
                    String[] genreStrings = (String[]) genreArray.getArray();
                    movie.setGenre(Arrays.asList(genreStrings));
                } // Assuming genre is stored as text or array
                movie.setLanguage(rs.getString("language"));
                movie.setStatus(rs.getString("status"));
                movie.setImageUrl(rs.getString("image_url"));
                movies.add(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }



    //------------------------------------------------------------------------------------------------------------------------
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        String query = "SELECT * FROM movie WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setAgeRange(resultSet.getString("age_range"));
                movie.setImageUrl(resultSet.getString("image_url"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setTrailarUrl(resultSet.getString("trailar_url"));
                movie.setPrice(resultSet.getDouble("price"));
                movie.setStatus(resultSet.getString("status"));

                // Assuming genres are stored as a comma-separated string in the database
                String genreString = resultSet.getString("genre");
                movie.setGenre(List.of(genreString.split(",")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movie;
    }



    //---------------------------------------Update Movie-----------------------------------------------------------
    public boolean updateMovie(Movie movie) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;

        try {
            // Get the database connection
            connection = DBConnection.getConnection();

            // SQL query to update the movie
            String sql = "UPDATE movie SET title = ?, description = ?, language = ?, age_range = ?, trailar_url = ?, price = ?, status = ? WHERE id = ?";

            // Prepare the statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDescription());
            preparedStatement.setString(3, movie.getLanguage());
            preparedStatement.setString(4, movie.getAgeRange());
            preparedStatement.setString(5, movie.getTrailarUrl());
            preparedStatement.setDouble(6, movie.getPrice());
            preparedStatement.setString(7, movie.getStatus());
            preparedStatement.setInt(8, movie.getId());

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }



    //---------------------------------- Fetch movies by status------------------------------------------
    public List<Movie> getMoviesByStatus(String status) {
        List<Movie> movies = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get a connection to the database
            connection = DBConnection.getConnection();

            // Prepare SQL query
            String sql = "SELECT * FROM movie WHERE status = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDescription(resultSet.getString("description"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setAgeRange(resultSet.getString("age_range"));
                movie.setImageUrl(resultSet.getString("image_url"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setTrailarUrl(resultSet.getString("trailar_url"));
                movie.setPrice(resultSet.getDouble("price"));
                movie.setStatus(resultSet.getString("status"));

                // Convert genres (PostgreSQL text[] to List<String>)
                String[] genresArray = (String[]) resultSet.getArray("genre").getArray();
                movie.setGenre(List.of(genresArray));

                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movies;
    }



    // ----------------------------------- Movie Delete ------------------------------------
    public boolean deleteMovie(int movieId) throws SQLException {
        String deleteQuery = "DELETE FROM movie WHERE id = ?";
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





}
