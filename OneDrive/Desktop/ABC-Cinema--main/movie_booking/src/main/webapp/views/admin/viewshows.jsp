<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Movie Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="styles.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <style>
        .nav-link {
            border-radius: 10px;
        }
        .colorButton {
            cursor: pointer;
        }
        .colorButton:hover {
            background-color: #fbbf24;
            color: black;
        }
        .status-dot {
            height: 10px;
            width: 10px;
            border-radius: 50%;
            display: inline-block;
        }
        .status-active {
            background-color: yellow;
        }
        .status-inactive {
            background-color: red;
        }
        .action-btn {
            cursor: pointer;
            padding: 6px 10px;
            border-radius: 4px;
            margin: 0 3px;
        }
        .btn-add-movie {
            background-color: #fbbf24;
            color: black;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        .btn-add-movie:hover {
            background-color: #ffd85e;
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
    <aside class="w-5/6 bg-gray-300 p-6"style="height:80vh">
        <main class="w-full">
            <div class="flex justify-between items-center mb-6">
                <h2 class="text-2xl font-bold">Show Details</h2>
            </div>
            <div class="bg-gray-200 p-4 rounded"style="height: 65vh;overflow-y: auto ">
                <table class="w-full text-left border-collapse">
                    <thead>
                    <tr>
                        <th class="border py-2 px-4">Show ID</th>
                        <th class="border py-2 px-4">Movie Id</th>
                        <th class="border py-2 px-4">Show Date</th>
                        <th class="border py-2 px-4">Show Time</th>
                        <th class="border py-2 px-4">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Example rows (replace with dynamic data as needed) -->
                   <c:forEach var="show" items="${shows}">
                    <tr>
                        <td class="border py-2 px-4">${show.id}</td>
                        <td class="border py-2 px-4">${show.movieId}</td>
                        <td class="border py-2 px-4">${show.date}</td>
                        <td class="border py-2 px-4">${show.time}</td>
                        <td class="border py-2 px-4">
                            <a href="javascript:void(0);"
                               onclick="if(confirm('Are you sure you want to delete this showtime?')) { window.location.href = '${pageContext.request.contextPath}/admin/movie-management?action=deleteShowtime&showId=${show.id}'; }"
                               class="action-btn bg-red-500 text-white">
                                <i class="fas fa-trash"></i> Delete
                            </a>
                        </td>
                    </tr>
                   </c:forEach>
                    <!-- Repeat for more rows -->
                    </tbody>
                </table>
            </div>
        </main>
    </aside>
</div>
</body>
</html>

