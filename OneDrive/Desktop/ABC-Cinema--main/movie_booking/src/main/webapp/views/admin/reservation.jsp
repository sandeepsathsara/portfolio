<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.movie_booking.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Bookings</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .nav-link {
            border-radius: 10px;
        }
        .colorButton:hover {
            background-color: #fbbf24; /* Highlight navigation item */
            color: black;
        }
        .active-btn {
            background-color: #10b981; /* Green for active */
            color: white;
        }
        .inactive-btn {
            background-color: #ef4444; /* Red for inactive */
            color: white;
        }
        .empty-message {
            text-align: center;
            padding: 20px;
            font-size: 18px;
            color: #4B5563; /* Gray text for empty message */
        }
    </style>
</head>
<body class="bg-gradient-to-b from-black to-purple-900 min-h-screen flex flex-col">

<header class="text-white items-center p-3">
    <img alt="logo.png" class="mr-2 h-20" src="https://i.ibb.co/XDWNCbt/image.png"/>
</header>

<div class="flex flex-1" >
    <!-- Include Navigation Bar -->

    <jsp:include page="/components/admin-navbar.jsp"/>

    <!-- Main Content -->
    <main class="w-5/6 bg-gray-300 p-2"style="height:80vh">
        <h2 class="text-2xl font-bold mb-12">Bookings</h2>
        <div class="bg-gray-200 p-6 py-4" style="height: 65vh;overflow-y: auto ">
            <c:choose>
                <c:when test="${not empty bookings}">
                    <table class="w-full text-left border-collapse">
                        <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>User</th>
                            <th>Movie Name</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Seats</th>
                            <th>Total Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${bookings}" var="booking">
                            <tr>
                                <td>${booking.bookingId}</td>
                                <td>${booking.userId}</td>
                                <td>${booking.movieName}</td>
                                <td>${booking.date}</td>
                                <td>${booking.time}</td>
                                <!-- Debug to check type and content -->
                                <td>

                                    <c:out value="${booking.seatNumbers}"/>
                                </td>



                                <td>${booking.totalPrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="empty-message">No bookings found.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
</body>
</html>
