<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset Verification - ABC Cinema</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #000000 0%, #4B0082 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .verification-container {
            background: white;
            border-radius: 8px;
            padding: 40px;
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
        }

        .form-group {
            margin-top: 20px;
        }

        input[type="text"], button {
            padding: 12px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }

        input[type="text"] {
            width: 90%;
            background: #f0efef;
            color: #333;
        }

        button {
            width: 95%;
            background-color: #FF0000;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border: none;
        }

        button:hover {
            background-color: #cc0000;
        }

        .info-text {
            margin-top: 15px;
            font-size: 14px;
            color: #555;
        }

        .info-text a {
            color: #007BFF;
            text-decoration: none;
        }

        .info-text a:hover {
            text-decoration: underline;
        }

        .alert {
            width: 100%;
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="verification-container">
    <h1>Password Reset Verification</h1>
    <p>Please enter the verification code sent to <strong><%= request.getParameter("email") %></strong> to proceed with resetting your password.</p>
    <%-- Display error or success messages from servlet --%>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="<%= request.getContextPath() %>/auth/verify-password-reset-code" method="post">
        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
        <div class="form-group">
            <input type="text" name="code" placeholder="Verification Code" required>
        </div>
        <button type="submit">Verify Code</button>
    </form>
    <p class="info-text">Didn't receive the code? <a href="<%= request.getContextPath() %>/auth/resend-code?email=<%= request.getParameter("email") %>">Click here to resend</a>.</p>
</div>
</body>
</html>
