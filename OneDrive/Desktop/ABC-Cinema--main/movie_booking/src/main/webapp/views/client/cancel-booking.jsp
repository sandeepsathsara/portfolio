<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Cancellation - ABC Cinema</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
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
        .form-fields {
            display: flex;
            flex-direction: column;
        }
        .form-group label {
            display: block;
            color: #333; /* Dark label for contrast on light background */
        }
        .form-group input {
            width: 95%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            margin-top: 5px;
            background: #f0efef; /* Light grey background for inputs */
        }
        .cancel-btn, .back-btn {
            background-color: #FF0000; /* Primary action in red */
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin: 10px auto;
        }
        .cancel-btn:hover{
            background-color: #cc0000; /* Darker red on hover */
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Booking Cancellation</h1>
    <div class="form-fields">
        <form action="${pageContext.request.contextPath}/cancel-booking" method="post">
            <div class="form-group">
                <label for="booking-id">Booking ID:</label>
                <input type="text" id="booking-id" name="bookingId" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button type="submit" class="cancel-btn">Cancel Booking</button><br>
        </form>
    </div>
</div>

</body>
</html>
