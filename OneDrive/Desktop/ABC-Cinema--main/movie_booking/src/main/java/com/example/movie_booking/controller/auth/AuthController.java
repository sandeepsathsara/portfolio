package com.example.movie_booking.controller.auth;

import com.example.movie_booking.model.User;
import com.example.movie_booking.service.AuthService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/auth/register",
        "/auth/login",
        "/auth/forgot-password",
        "/auth/enter-code",
        "/auth/verify-email",
        "/auth/verify-email-password",
        "/auth/verify-password-reset-code",
        "/auth/reset-password",
        "/auth/verify-code",
        "/auth/password-reset-verification",
        "/auth/send-password-reset-code",
        "/logout"
})
public class AuthController extends HttpServlet {

    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        switch (action) {
            case "/auth/register":
                forwardToPage(request, response, "/views/auth/sign-up.jsp");
                break;
            case "/auth/login":
                forwardToPage(request, response, "/views/auth/sign-in.jsp");
                break;
            case "/auth/verify-email":
                forwardToPage(request, response, "/views/auth/email-verification.jsp");
                break;
            case "/auth/forgot-password":
                forwardToPage(request, response, "/views/auth/enter-email-for-password-reset.jsp");
                break;
            case "/auth/password-reset-verification":
                forwardToPage(request, response, "/views/auth/password-reset-verification.jsp");
                break;
            case "/auth/reset-password":
                forwardToPage(request, response, "/views/auth/reset-password.jsp");
                break;
            case "/logout":
                logoutUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        switch (action) {
            case "/auth/register":
                registerUser(request, response);
                break;
            case "/auth/login":
                loginUser(request, response);
                break;
            case "/auth/send-password-reset-code":
                sendPasswordResetCode(request, response);
                break;
            case "/auth/verify-code":
                verifyEmailCode(request, response);
                break;
            case "/auth/reset-password":
                resetPassword(request, response);
                break;
            case "/auth/verify-password-reset-code":
                verifyPasswordResetCode(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String mobileStr = request.getParameter("mobile");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            forwardToPage(request, response, "/views/auth/sign-up.jsp");
            return;
        }

        try {
            int mobile = Integer.parseInt(mobileStr);
            boolean isRegistered = authService.registerUser(fullName, email, mobile, password,"CLIENT",false,false);
            if (isRegistered) {
                response.sendRedirect(request.getContextPath() + "/auth/verify-email?email=" + email);
            } else {
                request.setAttribute("error", "Registration failed. Please try again!");
                forwardToPage(request, response, "/views/auth/sign-up.jsp");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid mobile number format.");
            forwardToPage(request, response, "/views/auth/sign-up.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "An internal error occurred during registration."+e.getMessage());
            forwardToPage(request, response, "/views/auth/sign-up.jsp");
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Check for hardcoded admin credentials
            if (email.equals("admin@gmail.com") && password.equals("admin123")) {
                response.sendRedirect(request.getContextPath() + "/admin/movie-management");
                return; // Important to prevent further processing
            }

            // Proceed with normal authentication
            User user = authService.loginUser(email, password);
            if (user == null) {
                request.setAttribute("error", "Invalid email or password.");
                forwardToPage(request, response, "/views/auth/sign-in.jsp");
            } else if (!user.isEmailVerified()) {
                request.setAttribute("error", "Please verify your email before logging in.");
                forwardToPage(request, response, "/views/auth/sign-in.jsp");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                session.setMaxInactiveInterval(3600); // one hour
                // Redirect based on the user role
                if (user.getRole().equals("CLIENT")) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "An internal error occurred. Please try again.");
            forwardToPage(request, response, "/views/auth/sign-in.jsp");
        }
    }
    private void sendPasswordResetCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        try {
            boolean emailSent = authService.sendForgotPasswordCode(email);
            if (emailSent) {
                response.sendRedirect(request.getContextPath() + "/auth/password-reset-verification?email=" + email);
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp?error=User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            response.sendRedirect(request.getContextPath() + "/error.jsp?error=Failed to send password reset email. " + e.getMessage());
        }
    }

    private void verifyEmailCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        try {
            int userId = authService.getUserIdByEmail(email);
            boolean isVerified = authService.verifyCode(userId, code);

            if (isVerified) {
                request.setAttribute("success", "Email verified successfully. You can now log in.");
                forwardToPage(request, response, "/views/auth/email-verification-success.jsp");
            } else {
                request.setAttribute("error", "Invalid or expired verification code. Please try again.");
                forwardToPage(request, response, "/views/auth/email-verification.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An internal error occurred. Please try again later.");
            forwardToPage(request, response, "/views/auth/email-verification.jsp");
        }
    }

    private void verifyPasswordResetCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logic to handle verification of the reset password code.
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if (authService.verifyCode(authService.getUserIdByEmail(email), code)) {
            request.setAttribute("email", email); // Pass email forward to reset password page
            request.setAttribute("code", code); // Pass code forward to reset password page
            forwardToPage(request, response, "/views/auth/reset-password.jsp");
        } else {
            request.setAttribute("error", "Invalid or expired code.");
            forwardToPage(request, response, "/views/auth/verify-email-for-password.jsp");
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            forwardToPage(request, response, "/views/auth/reset-password.jsp");
            return;
        }

        if (authService.resetPassword(email, code, newPassword)) {
            request.setAttribute("success", "Password reset successfully. You can now log in.");
            forwardToPage(request, response, "/views/auth/password-reset-success.jsp");
        } else {
            request.setAttribute("error", "Failed to reset password. Please try again.");
            forwardToPage(request, response, "/views/auth/reset-password.jsp");
        }
    }

    //logout user
    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // Get the session if it exists, no session is created if it doesn't exist
        if (session != null) {
            System.out.println("Invalidating session for user: " + session.getAttribute("username"));
            session.invalidate(); // Invalidate the session to clear all attributes
        } else {
            System.out.println("No session to invalidate.");
        }
        response.sendRedirect(request.getContextPath() + "/home"); // Redirect to the home page
    }
}
