<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Inquiries</title>
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
        .action-btn {
            cursor: pointer;
            padding: 6px 10px;
            border-radius: 4px;
            margin: 0 3px;
        }
        .action-btn:hover {
            background-color: #ffd85e;
        }
        .table-container {
            background-color: #e5e5e5;
            padding: 20px;
            border-radius: 10px;
        }
        .table th, .table td {
            border: 1px solid #ccc;
            padding: 12px 16px;
            text-align: left;
        }
        .table th {
            background-color: #f4f4f4;
            font-weight: bold;
        }
        .table td {
            background-color: white;
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
    <aside class="w-5/6 bg-gray-300 p-6" style="height:80vh">
        <main class="w-full">
            <h2 class="text-2xl font-bold mb-6">Inquiries</h2>
            <div class="bg-gray-200 p-4 rounded"style="height:65vh;overflow-y: auto">
                <table class="w-full text-left border-collapse">
                    <thead>
                    <tr>
                        <th class="border py-2 px-4">Name</th>
                        <th class="border py-2 px-4">Email</th>
                        <th class="border py-2 px-4">Message</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Displaying feedback dynamically -->
                    <c:forEach var="message" items="${contactMessages}">
                        <tr>
                            <td class="border py-2 px-4">${message.name}</td>
                            <td class="border py-2 px-4">${message.email}</td>
                            <td class="border py-2 px-4">${message.message}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </aside>
</div>
</body>
</html>
