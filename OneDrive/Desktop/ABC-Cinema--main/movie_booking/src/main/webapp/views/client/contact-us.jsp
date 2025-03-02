<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Thor Love & Thunder</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="styles.css" rel="stylesheet"/> <!-- Link to external CSS file -->

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(to bottom, #000000, #4B0082);
            color: #FFFFFF;
        }
        .container {
            width: 100%;
            margin: 0 auto;
        }
        /* Navbar Styles */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 40px;
            background-color: rgba(0, 0, 0, 0.9); /* Dark background with opacity */
        }
        .logo {
            display: flex;
            align-items: center;
            font-size: 24px;
            font-weight: bold;
        }
        .logo img {
            width: 40px;
            height: 40px;
            margin-right: 10px;
        }
        .logo span {
            color: #FFD700; /* Golden color for "ABC" */
        }
        nav ul {
            display: flex;
            list-style: none;
            margin: 0;
            padding: 0;
        }
        nav ul li {
            margin: 0 20px;
        }
        nav ul li a {
            color: #FFFFFF;
            text-decoration: none;
            font-weight: 500;
            font-size: 16px;
        }

        /* User Actions Styles */
        .user-actions {
            display: flex;
            align-items: center;
        }
        .user-actions .search, .user-actions .notifications {
            margin-right: 20px;
            font-size: 20px;
            color: white;
            cursor: pointer;
        }
        .user-profile {
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .user-profile img {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            margin-right: 10px;
            background-color: #ccc; /* Fallback color for the profile icon */
        }
        .user-profile span {
            font-size: 14px;
            color: #FFFFFF;
            font-weight: 500;
        }

        /* Dropdown Menu Styles */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #fff;
            color: #000;
            min-width: 100px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            text-align: center;
            border-radius: 5px;
        }
        .dropdown-content a {
            text-decoration: none;
            color: #000;
            padding: 8px 10px;
            display: block;
            font-size: 14px;
        }
        .dropdown-content a:hover {
            background-color: #ddd;
        }
        .dropdown:hover .dropdown-content {
            display: block; /* Show dropdown content on hover */
        }

        /* Footer Styles */
        footer {
            background-color: rgba(0, 0, 0, 0.3);
            padding: 20px 40px;
        }
        .footer-content {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }
        .footer-logo {
            display: flex;
            align-items: center;
        }
        .footer-logo img {
            width: 30px;
            margin-right: 10px;
        }
        .footer-logo span {
            color: #FFD700;
            font-weight: bold;
        }
        .footer-links a {
            color: #FFFFFF;
            text-decoration: none;
            margin-right: 20px;
        }
        .social-icons {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        .social-icons span {
            margin-bottom: 10px;
        }
        .social-icons-container {
            display: flex;
        }
        .social-icons a {
            color: #FFFFFF;
            margin-right: 15px;
            font-size: 20px;
        }
        .copyright {
            text-align: center;
            padding-top: 30px;
            color: #B8B8B8;
            font-size: 14px;
        }
        hr {
            border: 0;
            height: 1px;
            background-color: rgba(255, 255, 255, 0.2);
            margin: 30px 0;
        }
        main {
            border-radius: 15px;
            padding: 30px;
            margin-top: 20px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
        }
        .cinema-image {
            width: 100%;
            max-width: 800px;
            height: auto;
            display: block;
            margin: 30px auto;
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #FFD700; /* Golden color for a luxurious feel */
            margin-bottom: 20px;
        }
        p {
            padding: 0 20px;
            margin-bottom: 20px;
            font-size: 18px;
            line-height: 1.8;
            text-align: justify;
        }
        .contact-container {
            width: 100%;
            max-width: 400px;
            padding: 30px;
            background-color: rgba(45, 8, 95, 0.5);
            backdrop-filter: blur(10px);
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0,0,0,0.2);
            margin: 30px auto;
        }

        .contact-header {
            text-align: center;
            margin-bottom: 30px;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }

        .contact-header h2 {
            margin: 0;
            font-size: 24px;
        }

        .contact-header img {
            width: 20px;
            height: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 12px;
            background: white;
            border: none;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 16px;
        }

        textarea {
            height: 120px;
            resize: none;
        }

        button {
            width: 100%;
            background-color: #FFD700;
            color: #000;
            padding: 15px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            transition: transform 0.2s;
        }

        button:hover {
            transform: scale(1.02);
            background-color: #FFE44D;
        }

        .message {
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
<div class="container">

    <!-- Navbar -->
    <nav>
        <jsp:include page="/components/client-navbar.jsp" />
    </nav>

    <div class="contact-container">
        <div class="contact-header">
            <h2>Contact Us</h2>
        </div>
        <!-- Display success or error message -->
        <c:if test="${not empty success}">
            <div class="message success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/contact-us" method="post">
            <div class="form-group">
                <input type="text" name="name" placeholder="Name" required>
            </div>
            <div class="form-group">
                <input type="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <textarea name="message" placeholder="Message" required></textarea>
            </div>
            <button type="submit">Submit</button>
        </form>
    </div>

    <footer>
        <jsp:include page="/components/client-footer.jsp" />
    </footer>
</div>


</body>
</html>

