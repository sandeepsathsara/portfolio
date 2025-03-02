<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Password Reset - ABC Cinema</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #000000 0%, #4B0082 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .email-container {
            background: white;
            padding: 40px;
            border-radius: 8px;
            width: 100%;
            max-width: 400px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            color: #666;
            font-size: 16px;
            margin-bottom: 20px;
        }

        input[type="email"], button {
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
            display: block;
        }

        input[type="email"] {
            background: #f0efef;
            width: 95%;

        }

        button {
            background-color: #FF0000;
            color: white;
            cursor: pointer;
            width: 100%;

        }

        button:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>
<div class="email-container">
    <h1>Reset Your Password</h1>
    <p>Please enter your email address to receive a password reset code.</p>
    <form action="<%= request.getContextPath() %>/auth/send-password-reset-code" method="post">
        <input type="email" name="email" placeholder="Email Address" required>
        <button type="submit">Send Code</button>
    </form>
</div>
</body>
</html>
