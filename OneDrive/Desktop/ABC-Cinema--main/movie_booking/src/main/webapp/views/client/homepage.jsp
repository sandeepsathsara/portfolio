<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


        .hero {
            background: linear-gradient(rgba(15, 5, 30, 0.9), rgba(15, 5, 30, 0.9));
            background-size: contain;
        }


        .hero-content {
            position: relative;
            z-index: 2;
            color: white;
            padding: 50px 100px;
            display: flex;
            gap: 30px;
        }
        .movie-poster {
            width: 40vh;
            border-radius: 10px;
            border: 0.03px solid #d2d0d0;
        }
        .movie-poster img {
            width: 100%;
            border-radius: 10px;
        }
        .movie-info {
            padding-top: 205px;
            flex: 1;
        }
        .movie-title {
            font-size: 48px;
            margin-bottom: 20px;
        }
        .movie-description {
            margin-bottom: 20px;
            line-height: 1.5;
        }
        .movie-meta {
            display: flex;
            gap: 20px;
            align-items: center;
            margin-bottom: 20px;
        }
        .age-rating {
            background-color: #ff0000;
            padding: 2px 8px;
            border-radius: 3px;
        }
        .rating {
            color: #ffd700;
        }
        .genre {
            color: #888;
        }
        .buttons {
            display: flex;
            gap: 20px;
        }
        .book-now, .watch-trailer {
            padding: 10px 25px;
            border-radius: 30px;
            text-decoration: none;
            font-weight: bold;
        }
        .book-now {
            background-color: #ff0000;
            color: white;
        }
        .watch-trailer {
            background-color: white;
            color: black;
        }
        .now-available {
            padding: 50px;
        }
        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        .view-all {
            color: white;
            text-decoration: none;
        }
        .movie-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 30px;
        }
        .movie-card img {
            width: 100%;
            border-radius: 10px;
        }
        .movie-card-info {
            margin-top: 10px;
        }
        .about-section {
            background: linear-gradient(rgba(26, 9, 51, 0.8), rgba(26, 9, 51, 0.8)), url('https://i.ibb.co/1qnwZGP/image-3.png');
            background-size: cover;
        }
        .about-content {
            padding: 150px;
            line-height: 1.6;
        }


    </style>
</head>
<body>
<div class="container">

    <!-- Navbar -->
    <nav>
        <jsp:include page="/components/client-navbar.jsp" />
    </nav>


    <!-- Hero Section -->
    <section class="hero">
        <c:forEach var="heroMovie" items="${movies}" begin="0" end="0">
            <div class="hero-content" style="background: url('${pageContext.request.contextPath}/DBImages/${heroMovie.imageUrl}') no-repeat center center / cover;">
            <div class="movie-poster">
                    <img src="${pageContext.request.contextPath}/DBImages/${heroMovie.imageUrl}" alt="${heroMovie.title} movie poster"  style="height:60vh" />
                </div>
                <div class="movie-info">
                    <h1 class="movie-title">${heroMovie.title}</h1>
                    <p class="movie-description">${heroMovie.description}</p>
                    <div class="movie-meta">
                        <span>${heroMovie.status}</span>
                        <span class="age-rating">${heroMovie.ageRange}</span>
                        <span class="rating">★ ${heroMovie.rating}</span>
                        <c:forEach var="genre" items="${heroMovie.genre}">
                            <span class="genre">${genre}</span>
                        </c:forEach>
                    </div>
                    <div class="buttons">
                        <a href="${pageContext.request.contextPath}/moviedetails?movieId=${heroMovie.id}" class="book-now">Movie Details</a>
                        <a href="${heroMovie.trailarUrl}" class="watch-trailer">Watch Trailer</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </section>






    <!-- Now Available Section -->
    <section class="now-available"style="padding: 5rem 8rem">
        <div class="section-header">
            <h2>Now Available</h2>
            <a href="${pageContext.request.contextPath}/movies" class="view-all">View All <i class="fas fa-arrow-right"></i></a>
        </div>
        <div class="movie-grid">
            <c:forEach var="movie" items="${movies}" begin="1" end="3">
                <div class="movie-card">
                    <img src="${pageContext.request.contextPath}/DBImages/${movie.imageUrl}" alt="${movie.title}" style="height: 60vh" alt="${movie.title}"/>
                    <div class="movie-card-info">
                        <h3>${movie.title}</h3><br>
                        <div class="movie-meta">
                            <span>${movie.status}</span>
                            <span class="age-rating">${movie.ageRange}</span>
                            <span class="rating">★ ${movie.rating}</span>
                        </div>
                        <div class="buttons">
                            <a href="${pageContext.request.contextPath}/moviedetails?movieId=${movie.id}" class="book-now">Movie Details</a>
                            <a href="${movie.trailarUrl}" class="watch-trailer">Watch Trailer</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>


    <!-- About Section -->
    <section class="about-section">
        <div class="about-content" style="margin-right: 20rem">
            <p>At <span style="color: #FFD700;">ABC Cinema</span>, we're dedicated to providing you with the ultimate cinematic experience. From world-class facilities to exceptional customer care, your comfort and enjoyment are always our top priorities. Let us take care of the details so you can sit back, relax, and immerse yourself in the magic of the movies.</p>
            <br/>
            <p>Experience the difference—only at <span style="color: #FFD700;">ABC Cinema</span></p>
        </div>
    </section>

    <!-- Footer -->
    <footer>
        <jsp:include page="/components/client-footer.jsp" />
    </footer>
</div>
</body>
</html>
