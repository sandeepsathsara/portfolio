package com.example.movie_booking;


import com.example.movie_booking.utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DatabaseTestServlet", urlPatterns = "/test-database")
public class DatabaseTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = DBConnection.getConnection()) { // Use try-with-resources
            if (connection != null && !connection.isClosed()) {
                out.println("<html><body>");
                out.println("<h1>Database Connection Successful!</h1>");
                out.println("<p>Connected to postgresql</p>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h1>Failed to Connect to the Database</h1>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            out.println("<html><body>");
            out.println("<h1>Database Connection Error</h1>");
            out.println("<p>SQL State: " + e.getSQLState() + "</p>");
            out.println("<p>Error Code: " + e.getErrorCode() + "</p>");
            out.println("<p>Message: " + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>General Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        }
    }
}
