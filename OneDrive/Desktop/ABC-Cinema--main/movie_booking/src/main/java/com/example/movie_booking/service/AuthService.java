package com.example.movie_booking.service;
import com.example.movie_booking.dao.UserDao;
import com.example.movie_booking.model.User;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides authentication-related services including user registration,
 * login, email verification, and password management.
 */
public class AuthService {
    private final UserDao userDao = new UserDao();
    private final VerificationCodeService verificationCodeService = new VerificationCodeService();
    private final EmailService emailService = new EmailService();
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    // Registers a new user and sends a verification email
    public boolean registerUser(String fullName, String email, int mobile, String password,String role,boolean isActive,boolean emailVerified) throws Exception {
        if (userDao.isEmailExists(email)) {
            throw new IllegalArgumentException("Email already exists. Please use a different email.");
        }

        int userId = userDao.addUser(fullName, email, mobile, password,role,isActive,emailVerified);
        if (userId != -1) {
            return sendVerificationCode(userId, email);
        }
        return false;
    }

    // Attempts to log in a user with the provided credentials
    public User loginUser(String email, String password) {
        return userDao.authenticateUser(email, password);
    }

    public int getUserIdByEmail(String email) {
        return userDao.getUserIdByEmail(email);
    }

    // Sends a verification email to the user
    public boolean sendVerificationCode(int userId, String email) throws Exception {
        String code = generateCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

        if (verificationCodeService.addVerificationCode(userId, code, expiresAt)) {
            try {
                return emailService.sendVerificationEmail(email, code);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    // Verifies the code entered by the user
    public boolean verifyCode(int userId, String code) {
        if (verificationCodeService.verifyCode(userId, code)) {
            verificationCodeService.markCodeAsUsed(userId, code);
            return userDao.activateUser(userId);
        }
        return false;
    }

    // Sends a reset code for the forgotten password
    public boolean sendForgotPasswordCode(String email) {
        int userId = userDao.getUserIdByEmail(email);
        if (userId == -1) {
            return false; // User with the email doesn't exist
        }

        String code = generateCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

        if (verificationCodeService.addVerificationCode(userId, code, expiresAt)) {
            try {
                return emailService.sendForgotPasswordEmail(email, code);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    // Resets the user's password
    public boolean resetPassword(String email, String code, String newPassword) {
        int userId = userDao.getUserIdByEmail(email);
        return userDao.updatePassword(userId, newPassword);


    }

    // Utility method to generate a 6-digit verification code
    private String generateCode() {
        return String.valueOf(100000 + (int) (Math.random() * 900000)); // Generate random 6-digit code
    }


}

