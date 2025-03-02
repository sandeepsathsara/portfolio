package com.example.movie_booking.service;
import com.example.movie_booking.dao.VerificationCodeDAO;
import com.example.movie_booking.model.VerificationCode;

import java.time.LocalDateTime;
import java.util.Optional;

public class VerificationCodeService {
    private final VerificationCodeDAO verificationCodeDAO = new VerificationCodeDAO();

    // Adds a new verification code for the user
    public boolean addVerificationCode(int userId, String code, LocalDateTime expiresAt) {
        return verificationCodeDAO.addVerificationCode(userId, code, expiresAt);
    }

    // Marks the verification code as used
    public boolean markCodeAsUsed(int userId, String code) {
        return verificationCodeDAO.markCodeAsUsed(userId, code);
    }

    // Verifies if the code provided by the user is valid
    public boolean verifyCode(int userId, String code) {
        Optional<VerificationCode> verificationCode = verificationCodeDAO.getVerificationCode(userId, code);
        return verificationCode.map(VerificationCode::isValid).orElse(false);
    }
}

