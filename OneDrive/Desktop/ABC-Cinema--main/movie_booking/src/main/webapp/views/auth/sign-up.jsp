<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ABC Cinema - Sign Up</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #000000 0%, #4B0082 100%);
            height: 100vh;
            display: flex;
        }

        .movie-posters {
            flex: 1;
            background-image: url('https://i.ibb.co/GvrV88t/wp10615928-1.png');
            background-size: cover;
            background-position: center;
            border-top-right-radius: 90px;
            border-bottom-right-radius: 90px;
        }

        .signup-container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 40px;
            color: white;
        }

        .logo {
            display: flex;
            align-items: center;
            margin-bottom: 40px;
        }

        .logo img {
            width: 50px;
            height: 50px;
            margin-right: 10px;
        }

        .logo-text {
            font-size: 32px;
            font-weight: bold;
        }

        .abc, .cinema {
            display: block;
            color: #FFD700;
            font-size: 16px;
            letter-spacing: 4px;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
        }

        .input-container {
            width: 100%;
            max-width: 400px;
        }

        input, button {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }

        input {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }

        button {
            background-color: #FF0000;
            color: white;
            cursor: pointer;
        }

        .back-btn {
            background-color: #757575;
            color: white;
            text-decoration: none;
        }

        .divider {
            width: 100%;
            max-width: 400px;
            text-align: center;
            margin: 20px 0;
            display: flex;
            align-items: center;
        }

        .divider::before, .divider::after {
            content: "";
            flex: 1;
            border-bottom: 1px solid white;
        }

        .divider span {
            padding: 0 10px;
        }

        .google-btn {
            background-color: white;
            color: #757575;
        }

        .google-btn i {
            margin-right: 10px;
            color: #4285F4;
        }

        .signin-link {
            margin-top: 20px;
            font-size: 14px;
        }

        .signin-link a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .status-message {
            margin-top: 15px;
            font-size: 16px;
        }

        .alert {
            width: 100%;
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="movie-posters"></div>
<div class="signup-container">
    <div class="logo">
        <img src="https://i.ibb.co/XDWNCbt/image.png" alt="Movie projector icon in white">
        <div class="logo-text">
            <span class="abc">ABC</span>
            <span class="cinema">CINEMA</span>
        </div>
    </div>
    <h1>Join ABC Cinema</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/auth/register" method="post">
        <div class="input-container">
            <input type="text" name="fullName" placeholder="Full Name" required>
            <input type="email" name="email" placeholder="Email Address" required>
            <input type="tel" name="mobile" placeholder="Mobile Number" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
        </div>
        <button class="signup-btn" type="submit">Sign up</button>
    </form>
    <div class="divider"><span>OR</span></div>

    <div class="signin-link">
        Already have an account? <a href="${pageContext.request.contextPath}/auth/login">Sign in</a>.
    </div>
</div>
</body>
</html>
