<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Thor Love & Thunder|| Movies</title>
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


    </style>
</head>
<body>
<div class="container">

    <!-- Navbar -->
    <nav>
        <jsp:include page="/components/client-navbar.jsp" />
    </nav>

    <h1 style="padding: 40px">Your Search Result</h1>


    <!-- Now Available Section -->
    <section class="now-available" style="padding: 5rem 8rem">
        <div class="movie-grid">
            <c:forEach var="movie" items="${movies}" >
                <div class="movie-card">
                    <img src="${pageContext.request.contextPath}/DBImages/${movie.imageUrl}" style="height: 60vh" alt="${movie.title}"/>
                    <div class="movie-card-info">
                        <h3>${movie.title}</h3>
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




    <!-- Footer -->
    <footer>
        <jsp:include page="/components/client-footer.jsp" />
    </footer>
</div>
</body>
</html>
