<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmation - ABC Cinema</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"/>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #000000 0%, #4B0082 100%);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container {
            padding: 40px;
            width: 100%;
            max-width: 400px; /* Restricts max width while allowing smaller widths */
            background: white;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            position: relative;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        p {
            font-size: 16px;
            color: #666;
            line-height: 1.6;
        }
        .button {
            width: 100%;
            background-color: #FF0000;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #cc0000;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Booking Cancel!</h1>
    <p>Your booking has been successfully Canceled.</p>
    <p>You have received an email for more details.</p>
    <a href="${pageContext.request.contextPath}/home" class="button">Return to Home</a>
</div>
</body>
</html>
