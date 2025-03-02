<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        /* Navbar Styles */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 40px;
            background-color: rgba(0, 0, 0, 0.9); /* Dark background with opacity */
        }
        .logo {
            display: flex;
            align-items: center;
            font-size: 24px;
            font-weight: bold;
        }
        .logo img {
            width: 40px;
            height: 40px;
            margin-right: 10px;
        }
        .logo span {
            color: #FFD700; /* Golden color for "ABC" */
        }
        nav ul {
            display: flex;
            list-style: none;
            margin: 0;
            padding: 0;
        }
        nav ul li {
            margin: 0 20px;
        }
        nav ul li a {
            color: #FFFFFF;
            text-decoration: none;
            font-weight: 500;
            font-size: 16px;
        }

        /* User Actions Styles */
        .user-actions {
            display: flex;
            align-items: center;
        }
        .user-actions .search, .user-actions .notifications {
            margin-right: 20px;
            font-size: 20px;
            color: white;
            cursor: pointer;
        }
        .user-profile {
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .user-profile img {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            margin-right: 10px;
            background-color: #ccc; /* Fallback color for the profile icon */
        }
        .user-profile span {
            font-size: 14px;
            color: #FFFFFF;
            font-weight: 500;
        }

        /* Dropdown Menu Styles */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #fff;
            color: #000;
            min-width: 100px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            text-align: center;
            border-radius: 5px;
        }
        .dropdown-content a {
            text-decoration: none;
            color: #000;
            padding: 8px 10px;
            display: block;
            font-size: 14px;
        }
        .dropdown-content a:hover {
            background-color: #ddd;
        }
        .dropdown:hover .dropdown-content {
            display: block; /* Show dropdown content on hover */
        }

        /* Footer Styles */
        footer {
            background-color: rgba(0, 0, 0, 0.3);
            padding: 20px 40px;
        }
        .footer-content {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }
        .footer-logo {
            display: flex;
            align-items: center;
        }
        .footer-logo img {
            width: 30px;
            margin-right: 10px;
        }
        .footer-logo span {
            color: #FFD700;
            font-weight: bold;
        }
        .footer-links a {
            color: #FFFFFF;
            text-decoration: none;
            margin-right: 20px;
        }
        .social-icons {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        .social-icons span {
            margin-bottom: 10px;
        }
        .social-icons-container {
            display: flex;
        }
        .social-icons a {
            color: #FFFFFF;
            margin-right: 15px;
            font-size: 20px;
        }
        .copyright {
            text-align: center;
            padding-top: 30px;
            color: #B8B8B8;
            font-size: 14px;
        }
        hr {
            border: 0;
            height: 1px;
            background-color: rgba(255, 255, 255, 0.2);
            margin: 30px 0;
        }

        main {
            border-radius: 15px;
            padding: 30px;
            margin-top: 20px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
        }
        .cinema-image {
            width: 100%;
            max-width: 800px;
            height: auto;
            display: block;
            margin: 30px auto;
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #FFD700; /* Golden color for a luxurious feel */
            margin-bottom: 20px;
        }
        p {
            padding: 0 20px;
            margin-bottom: 20px;
            font-size: 18px;
            line-height: 1.8;
            text-align: justify;
        }
    </style>
</head>
<body>
<div class="container">

    <!-- Navbar -->
    <nav>
        <jsp:include page="/components/client-navbar.jsp" />
    </nav>

    <main>
        <h1>About Us <i class="fas fa-info-circle info-icon"></i></h1>
        <img src="https://i.ibb.co/P9nP6P8/output-1.png" alt="ABC Cinema Front" class="cinema-image"/>
        <p>Welcome to ABC Cinema, a cornerstone of entertainment excellence since our founding in 1975. Established by visionary entrepreneurs Mr. Manidu and Mr. Sathsara, who hold 51% and 49% shares respectively, ABC Cinema began with a passion for delivering world-class cinematic experiences to audiences across the nation. Today, we proudly operate over 10 state-of-the-art cinema halls strategically located around the country, providing unmatched comfort, cutting-edge technology, and immersive viewing experiences.</p>
        <p>Our journey has expanded beyond cinema halls, making ABC Cinema a leading movie production house that creates blockbuster hits which captivate audiences and showcase the finest talent in the country. We are excited to announce the appointment of Miss Bindya and Miss Kaveesha as new directors, joining the ranks of ABC Cinema's esteemed management, bringing fresh perspectives and innovative ideas.</p>
        <p>We invite you to be a part of our story, where the magic of movies continues to thrive. At ABC Cinema, we believe in the power of storytelling and its ability to inspire, entertain, and connect people. Experience the difference with us — where your comfort and enjoyment are always our top priorities.</p>
    </main>


    <!-- Navbar -->
    <footer>
        <jsp:include page="/components/client-footer.jsp" />
    </footer>
</div>
</body>
</html>
