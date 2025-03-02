<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Add Showtime</title>
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
        .form-container {
            background-color: #f3f3f3;
            padding: 20px;
            border-radius: 10px;
        }
        .input-field, .select-field {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .form-actions {
            margin-top: 20px;
            display: flex;
            justify-content: flex-start;
            gap: 15px;
        }
        .btn-submit {
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        .btn-submit:hover {
            background-color: #5bd662;
        }
        .btn-reset {
            background-color: #4a4a4a;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }
        .btn-reset:hover {
            background-color: #6b6b6b;
        }
         .success-message {
             color: green;
             background-color: #d4edda;
             border: 1px solid #c3e6cb;
             padding: 10px;
             margin-bottom: 15px;
             border-radius: 5px;
         }

        .error-message {
            color: red;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
        }
    </style>


</head>

<body class="bg-gradient-to-b from-black to-purple-900 min-h-screen flex flex-col">

<header class="text-white items-center p-3">
    <img alt="logo.png" class="mr-2 h-20" src="https://i.ibb.co/XDWNCbt/image.png"/>
</header>


    <!-- Include Navigation Bar -->
    <div class="flex flex-1">
        <jsp:include page="/components/admin-navbar.jsp"/>
    <!-- Main Content -->
    <aside class="w-5/6 bg-gray-300 p-6" style="height: 80vh">
        <main class="w-full">
            <h2 class="text-2xl font-bold mb-6">Add Showtime</h2>



            <%
                String message = request.getParameter("message");
                if (message != null && !message.isEmpty()) {
            %>
            <div class="success-message">
                <p><%= message %></p>
            </div>
            <%
                }
            %>


            <div class="form-container" style="height: 65vh;overflow-y: auto ">
                <form action="${pageContext.request.contextPath}/admin/movie-management" method="post">
                    <!-- Hidden Movie ID -->
                    <%
                        String movieId = request.getParameter("movieId");
                    %>
                    <input type="hidden" name="movieId" value="<%= movieId %>">
                    <input type="hidden" name="action" value="addShow">

                    <!-- Movie ID Display -->
                    <p><strong>Movie ID:</strong> <%= movieId %></p>

                    <!-- Date Field -->
                    <label for="date" class="block font-semibold">Date</label>
                    <input type="date" id="date" name="date" class="input-field" required>

                    <!-- Time Dropdown -->
                    <label for="time" class="block font-semibold">Time</label>
                    <select id="time" name="time" class="select-field" required>
                        <option value="" disabled selected>Select Time</option>
                        <option value="10:00 AM">10:00 AM</option>
                        <option value="01:00 PM">01:00 PM</option>
                        <option value="04:00 PM">04:00 PM</option>
                        <option value="07:00 PM">07:00 PM</option>
                        <option value="10:00 PM">10:00 PM</option>
                    </select>

                    <!-- Theater Dropdown -->
                    <label for="theater" class="block font-semibold">Theater</label>
                    <select id="theater" name="theater" class="select-field" required>
                        <option value="" disabled selected>Select Theater</option>
                        <option value="Theater 1">Theater 1</option>
                        <option value="Theater 2">Theater 2</option>
                        <option value="Theater 3">Theater 3</option>
                        <option value="Theater 4">Theater 4</option>
                    </select>

                    <!-- Form Actions -->
                    <div class="form-actions">
                        <button type="submit" class="btn-submit">Add Showtime</button>
                        <button type="reset" class="btn-reset">Reset</button>
                    </div>
                </form>
            </div>
        </main>
    </aside>
</div>

</body>
</html>

