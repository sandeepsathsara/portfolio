package com.example.movie_booking.controller.admin;

import com.example.movie_booking.config.AppConfig;
import com.example.movie_booking.model.Movie;
import com.example.movie_booking.model.Showtime;
import com.example.movie_booking.service.BookingService;
import com.example.movie_booking.service.MovieService;
import com.example.movie_booking.service.ShowtimeService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@WebServlet(urlPatterns = {
        "/admin/movie-management",

})
@MultipartConfig
public class MovieManagementController extends HttpServlet {

    MovieService movieService = new MovieService();
    ShowtimeService showtimeService = new ShowtimeService();
    private static final String UPLOAD_PATH = AppConfig.DB_IMAGES_PATH;

    public void dindDbPath(String dbPath) {
        if(dbPath != null) {
            System.out.println("dbpath are null");
        }
    }

    private static final String MODE = "sandbox";

    @Override
    public void init() {
        dindDbPath(UPLOAD_PATH);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


            // Default action to "showMovies" if not provided
            if (action == null || action.isEmpty()) {
                action = "showMovies";
            }

            switch (action) {
                case "showMovies":
                    showMovies(request, response);
                    break;
                case "viewShows":
                    viewShows(request, response);
                    break;
                case "updateForm":
                    showUpdateForm(request, response);
                    break;
                case "deleteMovie":
                    deleteMovie(request, response);
                    break;
                case "deleteShowtime":
                    deleteShowtime(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }







    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            return;
        }

        switch (action) {
            case "addMovie":
                addMovie(request, response);
                break;
            case "addShow":
                addShow(request, response);
                break;
            case "updateMovie":
                updateMovie(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

//-------------------------------------------------Add-Movie----------------------------------------------------------------------------------


    private void addMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get form data
            String title = request.getParameter("movieTitle");
            String description = request.getParameter("description");
            String[] genresArray = request.getParameterValues("genre");
            String language = request.getParameter("language");
            String ageRange = request.getParameter("ageRange");

            double rating = Double.parseDouble(request.getParameter("rating"));
            String trailerUrl = request.getParameter("trailerUrl");
            double price = Double.parseDouble(request.getParameter("price"));
            String status = request.getParameter("status");
            String imagePath = "/images/default_movie.png"; // Default image path
            // Convert genres array to list
            List<String> genres = genresArray != null ? List.of(genresArray) : new ArrayList<>();

            System.out.println("1");

            // Handle image upload
            Part filePart = request.getPart("poster");
            String imageFileName = "default_movie.png"; // Default file name

            System.out.println("1");

            if (filePart != null && filePart.getSize() > 0) {

                System.out.println("1");
                // Extract file name
                imageFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                File uploadDir = new File(UPLOAD_PATH);

                System.out.println("1");

                // Ensure upload directory exists
                if (!uploadDir.exists()) {
                    boolean created = uploadDir.mkdirs();
                    if (!created) {
                        throw new IOException("Failed to create upload directory: " + UPLOAD_PATH);
                    }
                }

                System.out.println("1");
                // Save file to the upload path
                String imageFilePath = UPLOAD_PATH + File.separator + imageFileName;
                try (InputStream inputStream = filePart.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(imageFilePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("1");

                } catch (IOException e) {
                    request.setAttribute("error", "Error uploading image: " + e.getMessage());
                    forwardToPage(request, response, "/views/admin/addmovie.jsp");
                    return;
                }
                System.out.println("2");
                // Update imagePath with the uploaded file path relative to the web app
                imagePath = imageFileName;
            }




            // Create Movie object
            Movie movie = new Movie(0, title, description, language, ageRange, imagePath, rating, trailerUrl, price, status, genres);



            // Add movie to database
            int movieId = movieService.addMovie(movie);



            if (movieId > 0) {

                System.out.println("4");
                // Redirect to add showtime page with the movie ID
                response.sendRedirect(request.getContextPath() + "/views/admin/addshowtime.jsp?movieId=" + movieId);

            } else {
                request.setAttribute("error", "Failed to add movie. Please try again.");
                request.getRequestDispatcher("/views/admin/addmovie.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/views/admin/addmovie.jsp").forward(request, response);
        }
    }



    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }




    //-------------------------------------- ADD-ShowTime-------------------------------------------
    private void addShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String time = request.getParameter("time");
            String theater = request.getParameter("theater");

            // Create Showtime object
            Showtime showtime = new Showtime(0, movieId, date, time, theater);

            // Save showtime to database
            boolean isAdded = showtimeService.addShowtime(showtime);
            String message;
            if (isAdded) {
                message = "Showtime added successfully!";
            } else {
                message = "Failed to add showtime. Please try again.";
            }

// URL-encode the message to handle special characters like spaces
            String encodedMessage = URLEncoder.encode(message, "UTF-8");

// Redirect to the add showtime page with movieId and message
            response.sendRedirect(request.getContextPath() + "/views/admin/addshowtime.jsp?movieId=" + movieId + "&message=" + encodedMessage);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/add_show.jsp").forward(request, response);
        }
    }



//-----------------------------------------------Show Movie ----------------------------------------
private void showMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {

        // Fetch all movies
        List<Movie> movies = movieService.getAllMovies();
        System.out.println(movies.isEmpty());
        // Set movies as request attribute
        request.setAttribute("movies", movies);

        // Forward to the JSP page
        forwardToPage(request, response, "/views/admin/movies.jsp");
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Failed to load movies. Please try again.");
        forwardToPage(request, response, "/views/admin/error.jsp");
    }
}




//------------------------------------ View Shows By Movie Id-----------------------------------------
private void viewShows(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("movieId"));

            List<Showtime> shows = showtimeService.getAllShows(id);
            System.out.println(shows.isEmpty());
            // Set movies as request attribute
            request.setAttribute("shows", shows);

            // Forward to the JSP page
            forwardToPage(request, response, "/views/admin/viewshows.jsp");

        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("error", "Failed to load movies. Please try again.");
            forwardToPage(request, response, "/views/admin/error.jsp");
        }


}

//------------------------------------------Show update form---------------------------------------------------------------

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("movieId"));
            System.out.println(id);

            Movie movie = movieService.getMovieById(id);

            if (movie != null) {

                request.setAttribute("movie", movie);
                forwardToPage(request, response, "/views/admin/updatemovie.jsp");
            } else {
                request.setAttribute("error", "Movie not found.");
                forwardToPage(request, response, "/views/admin/error.jsp");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid movie ID.");
            forwardToPage(request, response, "/views/admin/error.jsp");
        }


    }



    //-------------------------------Update -Movie ---------------------------
    private void updateMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        String movieId = request.getParameter("movieId");
        String title = request.getParameter("movieTitle");
        String description = request.getParameter("description");
        String language = request.getParameter("language");
        String ageRange = request.getParameter("ageRange");
        String trailerUrl = request.getParameter("trailerUrl");
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");

        System.out.println("2");

        Movie movie = new Movie();
        movie.setId(Integer.parseInt(movieId));
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setLanguage(language);
        movie.setAgeRange(ageRange);
        movie.setTrailarUrl(trailerUrl);
        movie.setPrice(price);
        movie.setStatus(status);


        System.out.println("3");

        boolean success = movieService.updateMovie(movie);

        System.out.println("4");

        if (success) {

            response.sendRedirect(request.getContextPath() + "/admin/movie-management?action=showMovies");
        } else {

            System.out.println("5");
            request.setAttribute("error", "update unscussefully");
          forwardToPage(request, response, "/views/admin/error.jsp");
        }

    }catch (Exception e){
        System.out.println(e.getMessage());
        request.setAttribute("error", "Invalid movie ID.");
        forwardToPage(request, response, "/views/admin/error.jsp");
    }
    }


    //------------------------------------------Delete Movie ------------------------------------------------
    private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the movieId parameter from the request
        String movieIdParam = request.getParameter("movieId");

        // Validate movieIdParam
        if (movieIdParam == null || movieIdParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie ID is required and cannot be empty.");
            return;
        }

        try {
            // Parse movieId to integer
            int movieId = Integer.parseInt(movieIdParam);

            // Debugging - Log the movieId
            System.out.println("Deleting movie with ID: " + movieId);

            // Call the service to delete movie and its showtimes
            boolean isDeleted = movieService.deleteMovieWithShowtimes(movieId);

            // Handle success or failure
            if (isDeleted) {
                request.setAttribute("successMessage", "Movie and its showtimes deleted successfully.");
                response.sendRedirect(request.getContextPath() + "/admin/movie-management?action=showMovies");
            } else {
                request.setAttribute("errorMessage", "Failed to delete the movie and its showtimes.");
                response.sendRedirect(request.getContextPath() + "/admin/movie-management?action=showMovies");
            }
        } catch (NumberFormatException e) {
            // Handle invalid movieId format
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Movie ID format. Must be an integer.");
        }
    }




    // ---------------------------------- Delete Show Time --------------------------------

    private void deleteShowtime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String showIdParam = request.getParameter("showId");

        if (showIdParam == null || showIdParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Show ID is required and cannot be empty.");
            return;
        }

        try {
            int showId = Integer.parseInt(showIdParam);

            // Debugging - Log the showId
            System.out.println("Deleting showtime with ID: " + showId);

            boolean isDeleted = showtimeService.deleteShowtime(showId);

            if (isDeleted) {
                request.setAttribute("successMessage", "Showtime deleted successfully.");
            } else {
                request.setAttribute("errorMessage", "Failed to delete the showtime.");
            }

            // Redirect to the appropriate page (e.g., showtime list)
            response.sendRedirect(request.getContextPath() + "/admin/movie-management?action=showMovies");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Show ID format. Must be an integer.");
        }
    }





}
