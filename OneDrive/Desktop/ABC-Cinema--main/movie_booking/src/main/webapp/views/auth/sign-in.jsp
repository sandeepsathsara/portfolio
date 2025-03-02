<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ABC Cinema - Login</title>
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

        .login-container {
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

        .abc {
            color: white;
        }

        .cinema {
            color: #FFD700;
            font-size: 16px;
            display: block;
            letter-spacing: 4px;
        }

        h1 {
            font-size: 42px;
            margin-bottom: 40px;
        }

        .input-container {
            width: 100%;
            max-width: 400px;
        }

        input {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }

        .forgot-password {
            color: white;
            text-decoration: none;
            font-size: 14px;
            margin: 10px 0 20px;
            display: block;
            text-align: right;
            width: 100%;
            max-width: 400px;
        }

        .login-btn, .back-btn, .google-btn {
            width: 100%;
            max-width: 400px;
            padding: 15px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin: 20px 0;
        }

        .login-btn {
            background-color: #FF0000;
            color: white;
            border: none;
        }

        .back-btn {
            background-color: #757575;
            color: white;
            text-decoration: none;
            text-align: center;
            text-decoration: none;
        }

        .google-btn {
            background-color: white;
            color: #757575;
            border: none;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .google-btn i {
            margin-right: 10px;
            color: #4285F4;
        }

        .signup-link {
            margin-top: 20px;
            font-size: 14px;
        }

        .signup-link a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .alert {
            width: 100%;
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="movie-posters"></div>
<div class="login-container">
    <div class="logo">
        <img src="https://i.ibb.co/XDWNCbt/image.png" alt="Movie projector icon in white">
        <div class="logo-text">
            <span class="abc">ABC</span>
            <span class="cinema">CINEMA</span>
        </div>
    </div>
    <h1>Welcome Back</h1>
    <%-- Display message from servlet --%>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="<%= request.getContextPath() %>/auth/login" method="post">
        <div class="input-container">
            <input type="email" name="email" placeholder="Email Address" required>
            <input type="password" name="password" placeholder="Password" required>
        </div>
        <a href="<%= request.getContextPath() %>/auth/forgot-password" class="forgot-password">Forget Password?</a>
        <button class="login-btn" type="submit">Log in</button>
    </form>
    <a class="back-btn" type="submit" href="<%= request.getContextPath() %>/home">Back to Home</a>
    <div class="divider"><span>OR</span></div>

    <div class="signup-link">
        Don't have an account? <a href="<%= request.getContextPath() %>/auth/register">Sign up now.</a>
    </div>
</div>
</body>
</html>
