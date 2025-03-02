<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 12/23/2024
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- navbar.jsp -->
<nav class="w-1/7 p-1">
    <ul>
        <li class="mb-4">

            <a href="<%= request.getContextPath() %>/admin/movie-management" class="nav-link block py-4 px-7 bg-gray-200 colorButton" align="center">Movies</a>

        </li>
        <li class="mb-4">
            <a href="<%= request.getContextPath() %>/admin/movie-bookings" class="nav-link block py-4 px-9 bg-gray-200 colorButton" align="center">Reservations</a>
        </li>
        <li class="mb-4">
            <a href="<%= request.getContextPath() %>/admin/feedbacks" class="nav-link block py-4 px-9 bg-gray-200 colorButton" align="center">Feedbacks</a>
        </li>
        <li class="mb-4">
            <a href="<%= request.getContextPath() %>/admin/user-management" class="nav-link block py-4 px-9 bg-gray-200 colorButton" align="center">Users</a>
        </li>
        <li class="mb-4">
            <a href="<%= request.getContextPath() %>/admin/inquiries" class="nav-link block py-4 px-9 bg-gray-200 colorButton" align="center">Inquiries</a>
        </li>
        <li class="mb-4">
            <a href="<%= request.getContextPath() %>/logout" class="nav-link block py-4 px-9 bg-gray-200 colorButton" align="center">Logout</a>
        </li>
    </ul>
</nav>

</body>
</html>
