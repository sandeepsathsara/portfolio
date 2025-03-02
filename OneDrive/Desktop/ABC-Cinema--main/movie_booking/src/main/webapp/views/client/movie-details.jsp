<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>ABC Cinema - Thor Love & Thunder</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(to bottom, #000000, #4B0082);
            color: white;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .container {
            flex: 1; /* Pushes footer to the bottom */
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 40px;
            background-color: rgba(0, 0, 0, 0.3);
        }

        .hero {
            position: relative;
            height: 500px;
            background-image: url('${pageContext.request.contextPath}/DBImages/${movie.imageUrl}');
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: flex-end;
            padding: 40px;
        }
        .hero-content {
            max-width: 50%;
        }
        .hero h1 {
            font-size: 48px;
            margin-bottom: 20px;
        }
        .hero-buttons {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        .hero-buttons a {
            padding: 10px 20px;
            border-radius: 30px;
            text-decoration: none;
            font-weight: bold;
        }
        .book-now {
            background-color: #FF0000;
            color: white;
        }
        .watch-trailer {
            background-color: white;
            color: black;
        }
        .movie-info {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }
        .movie-info span {
            display: flex;
            align-items: center;
        }
        .movie-info .rating {
            color: #FFD700;
        }
        .movie-info .age-rating {
            background-color: #FF0000;
            padding: 2px 5px;
            border-radius: 3px;
        }
        .movie-description {
            margin-bottom: 20px;
        }
        .recommendations {
            padding: 40px;
        }
        .recommendations-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .recommendations h2 {
            font-size: 24px;
        }
        .view-all a {
            color: white;
            text-decoration: none;
        }
        .recommendations-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .recommendation-item {
            position: relative;
        }
        .recommendation-item img {
            width: 100%;
            height: 50%;
            border-radius: 5px;
        }
        .recommendation-item .title {
            position: absolute;
            top: 55%;
            bottom: 10px;
            left: 10px;
            color: white;
            font-weight: bold;
        }
        .recommendation-item .episode {
            position: absolute;
            top: 10px;
            left: 10px;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 5px;
            border-radius: 3px;
        }

        footer {
            background-color: #1a1a1a;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: auto; /* Ensures the footer is pushed to the bottom */
        }

        footer a {
            color: #FFD700;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">

    <nav>
        <jsp:include page="/components/client-navbar.jsp" />
    </nav>

    <main>
        <section class="hero">
            <div class="hero-content">
                <!-- Movie Title -->
                <h1>${movie.title}</h1>

                <!-- Buttons for Booking and Watching Trailer -->
                <div class="hero-buttons">
                    <a href="${pageContext.request.contextPath}/book-now?id=${movie.id}" class="book-now">Book now</a>
                    <a href="${movie.trailarUrl}" class="watch-trailer" target="_blank">Watch Trailer</a>
                </div>

                <!-- Movie Metadata -->
                <div class="movie-info">
                    <span>${movie.language}</span>
                    <span class="age-rating">${movie.ageRange}</span>
                    <span class="rating"><i class="fas fa-star"></i> ${movie.rating}</span>
                    <c:forEach var="genre" items="${movie.genre}">
                        <span>${genre}</span>
                    </c:forEach>
                </div>

                <!-- Movie Description -->
                <p class="movie-description">
                    ${movie.description}
                </p>
            </div>
        </section>

        <!-- Movie Recommendations Section -->
        <section class="recommendations">
            <div class="recommendations-header">
                <h2>Recommended Movies</h2>
                <div class="view-all">
                    <a href="${pageContext.request.contextPath}/movies">View All</a>
                </div>
            </div>
            <div class="recommendations-grid">
                <c:forEach var="recommendation" items="${movies}" varStatus="status" begin="1" end="3">
                    <c:if test="${recommendation.id != movie.id}"> <!-- Ensure not showing the current movie -->
                        <div class="recommendation-item">
                            <img src="${pageContext.request.contextPath}/DBImages/${recommendation.imageUrl}" alt="${recommendation.title}">
                            <div class="title">${recommendation.title}</div>
                            <div class="episode">Season 1, Episode 1</div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </section>
    </main>

    <footer>
        <jsp:include page="/components/client-footer.jsp" />
    </footer>

</div>
</body>
</html>
