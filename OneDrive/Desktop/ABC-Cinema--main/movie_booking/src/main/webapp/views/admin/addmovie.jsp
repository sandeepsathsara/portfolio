<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Add Movie</title>
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
        .btn-add-movie, .btn-save, .btn-reset {
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            margin-right: 10px;
        }
        .btn-add-movie {
            background-color: #fbbf24;
            color: black;
        }
        .btn-add-movie:hover {
            background-color: #ffd85e;
        }
        .btn-save {
            background-color: #4caf50;
            color: white;
        }
        .btn-save:hover {
            background-color: #5bd662;
        }
        .btn-reset {
            background-color: #4a4a4a;
            color: white;
        }
        .btn-reset:hover {
            background-color: #6b6b6b;
        }
        .input-field, .select-field {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .checkbox-group {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }
        .checkbox-item {
            display: flex;
            align-items: center;
        }
        .form-grid {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: 20px;
            align-items: start;
        }
        .form-grid .label {
            font-weight: bold;
        }
        .form-actions {
            display: flex;
            justify-content: flex-start;
            gap: 20px;
        }
        .error-message {
            color: red;
            background-color: #f8d7da;
            border: 1px solid #f5c2c7;
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

<div class="flex flex-1">
    <!-- Include Navigation Bar -->
    <jsp:include page="/components/admin-navbar.jsp"/>

    <!-- Main Content -->
    <aside class="w-5/6 bg-gray-300 p-6"style="height:80vh">
        <main class="w-full" style="height: 65vh;overflow-y: auto ">
            <h2 class="text-2xl font-bold mb-6">Movie Details</h2>
            <c:if test="${not empty error}">
                <div class="error-message">
                    <p>${error}</p>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/admin/movie-management" method="post" enctype="multipart/form-data">
                <div class="form-grid">
                    <!-- Movie Title -->
                    <input type="hidden" name="action" value="addMovie">
                    <label for="movieTitle" class="label">Movie Title</label>
                    <input type="text" id="movieTitle" name="movieTitle" class="input-field" placeholder="Enter movie title">

                    <!-- Description -->
                    <label for="description" class="label">Description</label>
                    <textarea id="description" name="description" class="input-field" placeholder="Enter movie description"></textarea>

                    <!-- Genre -->
                    <label class="label">Genre</label>
                    <div class="checkbox-group">
                        <!-- Example checkboxes; dynamically populate based on genres -->
                        <div class="checkbox-item">
                            <input type="checkbox" id="genreAction" name="genre" value="Action">
                            <label for="genreAction" class="ml-2">Action</label>
                        </div>
                        <div class="checkbox-item">
                            <input type="checkbox" id="genreComedy" name="genre" value="Comedy">
                            <label for="genreComedy" class="ml-2">Comedy</label>
                        </div>
                        <div class="checkbox-item">
                            <input type="checkbox" id="genreDrama" name="genre" value="Drama">
                            <label for="genreDrama" class="ml-2">Drama</label>
                        </div>
                        <div class="checkbox-item">
                            <input type="checkbox" id="genreHorror" name="genre" value="Horror">
                            <label for="genreHorror" class="ml-2">Horror</label>
                        </div>
                        <div class="checkbox-item">
                            <input type="checkbox" id="genreThriller" name="genre" value="Thriller">
                            <label for="genreThriller" class="ml-2">Thriller</label>
                        </div>
                        <div class="checkbox-item">
                            <input type="checkbox" id="genreSciFi" name="genre" value="Sci-Fi">
                            <label for="genreSciFi" class="ml-2">Sci-Fi</label>
                        </div>
                    </div>

                    <!-- Language -->
                    <label for="language" class="label">Language</label>
                    <select id="language" name="language" class="select-field">
                        <option value="">Select Language</option>
                        <option value="English">English</option>
                        <option value="Spanish">Spanish</option>
                        <option value="French">French</option>
                    </select>

                    <!-- Age Range -->
                    <label for="ageRange" class="label">Age Range</label>
                    <select id="ageRange" name="ageRange" class="select-field">
                        <option value="">Select Age Range</option>
                        <option value="kids">kids</option>
                        <option value="18+">18+</option>
                        <option value="family">family</option>
                    </select>

                    <!-- Poster -->
                    <label for="poster" class="label">Poster</label>
                    <input type="file" id="poster" name="poster" class="input-field">

                    <!-- Rating -->
                    <label for="rating" class="label">Rating</label>
                    <select id="rating" name="rating" class="select-field">
                        <option value="">Select Rating</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>

                    <!-- Trailer URL -->
                    <label for="trailerUrl" class="label">Trailer URL</label>
                    <input type="url" id="trailerUrl" name="trailerUrl" class="input-field" placeholder="Enter trailer URL">

                    <!-- Price -->
                    <label for="price" class="label">Price</label>
                    <input type="number" id="price" name="price" class="input-field" placeholder="Enter price">

                    <!-- Status -->
                    <label class="label">Status</label>
                    <div>
                        <label>
                            <input type="radio" name="status" value="Active"> Active
                        </label>
                        <label class="ml-4">
                            <input type="radio" name="status" value="Inactive"> Inactive
                        </label>
                    </div>
                </div>

                <!-- Action Buttons -->
                <div class="form-actions mt-6">
                    <button type="submit" class="btn-add-movie">
                        <i class="fas fa-plus"></i> Add Movie
                    </button>
                    <button type="button" class="btn-save">
                        <i class="fas fa-save"></i> Save Changes
                    </button>
                    <button type="reset" class="btn-reset">
                        <i class="fas fa-redo"></i> Reset
                    </button>
                </div>
            </form>
        </main>
    </aside>
</div>
</body>
</html>
