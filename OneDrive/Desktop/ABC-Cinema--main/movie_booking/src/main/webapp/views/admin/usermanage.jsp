<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.example.movie_booking.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Users</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="styles.css">
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
    </style>
</head>
<body class="bg-gradient-to-b from-black to-purple-900 min-h-screen flex flex-col">

<header class="text-white items-center p-3">
    <img alt="logo.png" class="mr-2 h-20" src="https://i.ibb.co/XDWNCbt/image.png"/>
</header>

<div class="flex flex-1">
    <!-- Include Navigation Bar -->

    <jsp:include page="/components/admin-navbar.jsp"/>


    <!-- Main Content -->
    <main class="w-5/6 bg-gray-300 p-2"style="height:80vh">
        <h2 class="text-2xl font-bold mb-12">Users</h2>
        <div class="bg-gray-200 p-6 py-4"style="height:65vh;overflow-y: auto">
            <table class="w-full text-left border-collapse">
                <thead>
                <tr>
                    <th class="border py-2 px-4">ID</th>
                    <th class="border py-2 px-4">Full Name</th>
                    <th class="border py-2 px-4">Email</th>
                    <th class="border py-2 px-4">Mobile Number</th>
                    <th class="border py-2 px-4">Active</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr class="border-t">
                        <td class="py-2 px-4">${user.id}</td>
                        <td class="py-2 px-4">${user.fullName}</td>
                        <td class="py-2 px-4">${user.email}</td>
                        <td class="py-2 px-4">${user.mobile}</td>
                        <td class="py-2 px-4">
                            <form action="${pageContext.request.contextPath}/admin/user-management" method="post">
                                <input type="hidden" name="userId" value="${user.id}" />
                                <input type="hidden" name="action" value="toggleActive" />
                                <input type="hidden" name="isActive" value="${not user.active}" />
                                <button type="submit" class="${user.active ? 'inactive-btn' : 'active-btn'}">
                                        ${user.active ? 'Deactivate' : 'Activate'}
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${users.isEmpty()}">
                    <tr><td colspan="5" class="text-center py-2 px-4">No users found.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
