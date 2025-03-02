package com.example.movie_booking.controller.admin;

import com.example.movie_booking.model.User;
import com.example.movie_booking.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserManagementController", urlPatterns = {"/admin/user-management"})
public class AdminUserManagementController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        this.userService = new UserService(); // Ensure this constructor is correctly implemented in your UserService.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all users
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/admin/usermanage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("toggleActive".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            boolean active = Boolean.parseBoolean(request.getParameter("isActive"));
            // Correctly call the method to toggle the user's active state
            if (active) {
                userService.activateUser(userId);
            } else {
                userService.deactivateUser(userId);
            }
            response.sendRedirect(request.getContextPath() + "/admin/user-management"); // Redirect back to the user management page
        }
    }
}
