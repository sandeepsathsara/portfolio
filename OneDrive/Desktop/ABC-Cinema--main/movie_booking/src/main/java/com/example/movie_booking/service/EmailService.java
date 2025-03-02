package com.example.movie_booking.service;

import com.example.movie_booking.model.Booking;
import com.example.movie_booking.utils.EmailUtility;
import org.json.JSONObject;

public class EmailService {

    // Method to send verification email
    public boolean sendVerificationEmail(String toAddress, String verificationCode) throws Exception {
        String subject = "Email Verification Code";
        String message = getStyledEmailContent("Email Verification", verificationCode, "Verify Email", "http://localhost:8080/demo/verify-code.html");
       return  EmailUtility.sendEmail(toAddress, subject, message);

    }

    // Method to send password reset email
    public boolean sendForgotPasswordEmail(String toAddress, String resetCode) throws Exception {
        String subject = "Password Reset Code";
        String message = getStyledEmailContent("Password Reset", resetCode, "Reset Password", "http://localhost:8080/demo/reset-password.html");
        return EmailUtility.sendEmail(toAddress, subject, message);
    }

    // Method to send booking confirmation email
    public void sendBookingConfirmationEmail(String toAddress, Booking bookingDetails) throws Exception {
        String subject = "Booking Confirmation";
        // Ensure that the correct link is provided and the booking details are formatted correctly
        String message = getBookingEmailContent("Your Booking Confirmation", bookingDetails, "View Booking", "http://localhost:8080/demo/view-booking.html");

        // Debugging statement to confirm method invocation and parameters
        System.out.println("Sending email to: " + toAddress);
        System.out.println("Email Subject: " + subject);
        System.out.println("Email Message: " + message);

        // Call your EmailUtility's sendEmail method
        EmailUtility.sendEmail(toAddress, subject, message);

        // Debugging statement to confirm that the email has been sent
        System.out.println("Email sent successfully to: " + toAddress);
    }


    // Method to send booking cancellation email
    public void sendBookingCancellationEmail(String toAddress) throws Exception {
        String subject = "Booking Cancellation";
        String message = getCancellationEmailContent("Your Booking Cancellation","Contact Support", "http://localhost:8080/demo/contact-support.html");
        EmailUtility.sendEmail(toAddress, subject, message);
    }

    // Helper method to create styled email content
    private String getStyledEmailContent(String title, String code, String actionText, String actionLink) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>" + styleSheet() + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>" + title + "</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>We received a request for " + title.toLowerCase() + ". Please use the code below:</p>"
                + "            <div class='verification-code'>" + code + "</div>"
                + "            <a href='" + actionLink + "'>" + actionText + "</a>"
                + "            <p>If you did not request this, please ignore this email.</p>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Your Company. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }

    // Helper method for booking email content
    private String getBookingEmailContent(String title, Booking bookingDetails, String actionText, String actionLink) {
        // Extracting details from the Booking object
        String bookingId = bookingDetails.getBookingId(); // String type
        String movieName = bookingDetails.getMovieName();
        String date = bookingDetails.getDate();
        String time = bookingDetails.getTime();
        double totalPrice = bookingDetails.getTotalPrice();

        // Format the booking details into a string
        String details = String.format(
                "Booking ID: %s<br>Movie: %s<br>Date: %s<br>Time: %s<br>Total Price: Rs. %.2f",
                bookingId, movieName, date, time, totalPrice
        );

        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>" + styleSheet() + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>" + title + "</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>Thank you for your booking. Here are your booking details:</p>"
                + "            <p>" + details + "</p>"
                + "            <a href='" + actionLink + "' style='padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px;'>"
                + actionText + "</a>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 ABC Cinema. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }


    // Helper method for cancellation email content
    private String getCancellationEmailContent(String title, String actionText, String actionLink) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>" + styleSheet() + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>" + title + "</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>Your booking has been successfully canceled. Here are the details:</p>"
                + "            <p>Your money will be automatically refunded within 5-7 business days.</p>"
                + "            <a href='" + actionLink + "'>" + actionText + "</a>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Your Company. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }

    // Method to define common email style
    private String styleSheet() {
        return "    <style>"
                + "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                + "        .email-container { max-width: 600px; margin: 20px auto; background: white; border: 1px solid #ddd; border-radius: 5px; }"
                + "        .email-header { background-color: #007bff; color: white; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; }"
                + "        .email-body { padding: 20px; }"
                + "        .email-body p { font-size: 16px; color: #333; line-height: 1.5; }"
                + "        .email-body .verification-code { font-size: 24px; font-weight: bold; color: #007bff; margin: 20px 0; text-align: center; }"
                + "        .email-body a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; font-size: 16px; }"
                + "        .email-body a:hover { background-color: #0056b3; }"
                + "        .email-footer { text-align: center; padding: 10px; font-size: 12px; color: #777; background: #f9f9f9; border-top: 1px solid #ddd; }"
                + "    </style>";
    }
}
