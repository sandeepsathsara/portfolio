<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #000000 0%, #4B0082 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .error-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffdddd;
            border: 1px solid #cc0000;
            border-radius: 5px;
            color: #333;
            text-align: center;
        }

        .error-heading {
            color: #cc0000;
            margin-bottom: 10px;
        }

        .error-message {
            color: #666;
            line-height: 1.6;
        }

        .home-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4B0082;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1 class="error-heading">Error</h1>
    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>
    <c:if test="${empty error}">
        <p class="error-message">An unknown error occurred. Please try again later.</p>
    </c:if>
    <a href="${pageContext.request.contextPath}/home" class="home-link">Go to Homepage</a>
</div>
</body>
</html>
