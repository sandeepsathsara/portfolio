<%@ page import="com.example.movie_booking.model.User" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ABC Cinema</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #000;
            color: white;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #1e0635;
            padding: 20px;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 40px;
            background-color: rgba(0, 0, 0, 0.9); /* Ensure the header is visually distinct */
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
            display: block;
        }
        .logo {
            display: flex;
            align-items: center;
            color: #FFD700;
        }
        .logo img {
            margin-right: 10px;
        }
        nav ul {
            list-style-type: none;
            display: flex;
            padding: 0;
        }
        nav ul li {
            padding: 0 15px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            font-size: 16px;
        }
        nav ul li a:hover {
            color: #FFD700;
        }
        .user-actions {
            display: flex;
            align-items: center;
        }
        .search, .booking, .fas {
            color: white;
            margin-right: 20px;
            cursor: pointer;
        }
        .sign-in-button {
            background-color: red;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 30px;
            cursor: pointer;
            text-decoration: none;
        }
        .dropdown {
            margin-right: 10px;
            position: relative;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
        .cancel-booking-btn {
            display: inline-block;
            padding: 8px 12px;
            color: white;
            background-color: #ff4d4d;
            border-radius:30px;
            text-decoration: none;
            transition: background-color 0.3s;
            font-size: 16px;
            align-items: center;
            margin-right: 30px;
        }

        .cancel-booking-btn i {
            margin-right: 5px;
        }

        .cancel-booking-btn:hover {
            background-color: #cc0000;
        }
        .search-container form {
            display: flex;
        }

        .search-container input[type="text"] {
            padding: 8px;
            font-size: 16px;
            border: none;
            border-radius: 5px 0 0 5px;
            outline: none;
        }

        .search-container button {
            padding: 8px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            font-size: 16px;
        }

        .search-container button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

<%
    // Attempt to retrieve user object from session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // Fall back to checking cookies if no user in session
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    // For simplicity, just grabbing the username
                    request.setAttribute("userName", cookie.getValue());
                    break;
                }
            }
        }
    }
%>
<header>
    <div class="logo">
        <img src="https://i.ibb.co/XDWNCbt/image.png" alt="ABC Cinema logo with a movie camera icon"/>
        <span>ABC</span> CINEMA
    </div>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/movies">Movies</a></li>
            <li><a href="${pageContext.request.contextPath}/contact-us">Contact us</a></li>
            <li><a href="${pageContext.request.contextPath}/review-us">Review us</a></li>
            <li><a href="${pageContext.request.contextPath}/about-us">About us</a></li>
        </ul>
    </nav>

    <div class="search-container">
        <form action="<%= request.getContextPath() %>/movies" method="post">
            <input type="text" placeholder="Search movies..." class="searchInput" name="movie">
            <button type="submit"><i class="fas fa-search"></i> Search</button>
        </form>
    </div>

        <% if (user != null) { %>

        <!-- Link with icon for Cancel Booking -->
        <a href="<%= request.getContextPath() %>/cancel-booking" class="cancel-booking-btn">
            <i class="fas fa-times-circle"></i> My Booking
        </a>


        <div class="dropdown" >
            <%
                String fullName = user.getFullName();
                String displayName = fullName.length() > 10 ? fullName.substring(0, 10) + "..." : fullName;
            %>
            <span><%= displayName %></span>


            <div class="dropdown-content">
                <a href="<%= request.getContextPath() %>/logout">Sign Out</a>
            </div>
        </div>
        <% } else { %>
        <a class="sign-in-button" href="<%= request.getContextPath() %>/auth/login">Sign in</a>
        <% } %>
    </div>
</header>
</body>
</html>