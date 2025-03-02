package com.example.movie_booking.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.util.Properties;

public class EmailUtility {

    private static Properties emailProperties;

    static {
        emailProperties = new Properties();
        try {
            // Load properties from db.properties file
            emailProperties.load(EmailUtility.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load email configuration", e);
        }
    }

    // Common method to send email
    public static boolean sendEmail(String toAddress, String subject, String message) throws MessagingException {

        // Retrieve email configuration
        String host = emailProperties.getProperty("mail.smtp.host");
        String port = emailProperties.getProperty("mail.smtp.port");
        final String username = emailProperties.getProperty("mail.smtp.username");
        final String password = emailProperties.getProperty("mail.smtp.password");

        // SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Authenticator for username and password
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Create session
        Session session = Session.getInstance(properties, auth);

        // Create email message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        msg.setSubject(subject);
        msg.setContent(message, "text/html");

        // Send the email
        Transport.send(msg);
        return true;
    }
}
