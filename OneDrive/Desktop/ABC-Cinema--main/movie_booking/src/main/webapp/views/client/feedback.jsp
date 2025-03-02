<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Seat Booking - ${movie.title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="styles.css" rel="stylesheet"/> <!-- If you have external CSS -->
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


        /* FEEDBACK FORM CONTAINER */
        .feedback-form {
            background: #2d135e;
            padding: 30px;
            border-radius: 10px;
            width: 100%;
            max-width: 600px;
            margin: 30px auto; /* center horizontally with auto left/right */
        }

        .header-inputs {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 30px;
        }
        .input-group {
            position: relative;
        }
        .input-group input {
            width: 100%;
            padding: 10px 0;
            background: transparent;
            border: none;
            border-bottom: 1px solid #fff;
            color: #fff;
            outline: none;
        }
        .input-group label {
            position: absolute;
            left: 0;
            top: -20px;
            color: #fff;
            font-size: 14px;
        }

        h2 {
            margin-bottom: 30px;
            font-size: 24px;
        }

        /* QUESTION GROUPS + RATINGS */
        .question-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            cursor: pointer;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.2s;
        }
        .question-group:hover {
            background: rgba(255, 255, 255, 0.1);
        }
        .question {
            flex: 1;
            padding-right: 20px;
        }
        .rating {
            display: flex;
            gap: 15px;
        }
        .rating input[type="checkbox"] {
            appearance: none;
            width: 20px;
            height: 20px;
            border: 2px solid #fff;
            border-radius: 50%;
            cursor: pointer;
            transition: all 0.2s;
        }
        .rating input[type="checkbox"]:checked {
            background: #ffd700;
            border-color: #ffd700;
        }
        .rating input[type="checkbox"]:hover {
            transform: scale(1.1);
        }

        /* TEXTAREA & SUBMIT BUTTON */
        textarea {
            width: 100%;
            height: 100px;
            margin: 20px 0;
            padding: 15px;
            border-radius: 10px;
            border: none;
            resize: none;
            background: rgba(255, 255, 255, 0.9);
            color: #000;
            font-size: 14px;
        }
        .submitBtn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: #ffd700;
            color: #1e0b47;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            display: block;
            margin: 20px auto 0;
        }
        button:hover {
            background: #f0c700;
        }

        /* ALERT MESSAGES (SUCCESS / ERROR) */
        .alert {
            margin: 20px auto;
            padding: 15px;
            border-radius: 5px;
            max-width: 600px;
            text-align: center;
            font-weight: bold;
        }
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .alert-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
<div class="container">

    <!-- NAVBAR (search bar included) -->
    <nav>
        <jsp:include page="/components/client-navbar.jsp" />
    </nav>

    <!-- ALERT MESSAGES IF ANY -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <!-- FEEDBACK FORM -->
    <form class="feedback-form" action="${pageContext.request.contextPath}/review-us" method="post">
        <div class="header-inputs">
            <div class="input-group">
                <label>Name</label>
                <input type="text" name="name" required />
            </div>
            <div class="input-group">
                <label>Email</label>
                <input type="email" name="email" required />
            </div>
        </div>

        <h2>Send us your Feedback!</h2>

        <!-- Overall Experience -->
        <div class="question-group">
            <div class="question">How would you rate your overall experience?</div>
            <div class="rating">
                <label><input type="radio" name="experience" value="1" required /> 1</label>
                <label><input type="radio" name="experience" value="2" /> 2</label>
                <label><input type="radio" name="experience" value="3" /> 3</label>
                <label><input type="radio" name="experience" value="4" /> 4</label>
                <label><input type="radio" name="experience" value="5" /> 5</label>
            </div>
        </div>

        <!-- Ticket Reservation Ease -->
        <div class="question-group">
            <div class="question">Was the online ticket reservation process easy to use?</div>
            <div class="rating">
                <label><input type="radio" name="ticketEase" value="1" required /> 1</label>
                <label><input type="radio" name="ticketEase" value="2" /> 2</label>
                <label><input type="radio" name="ticketEase" value="3" /> 3</label>
                <label><input type="radio" name="ticketEase" value="4" /> 4</label>
                <label><input type="radio" name="ticketEase" value="5" /> 5</label>
            </div>
        </div>

        <!-- Online Payments -->
        <div class="question-group">
            <div class="question">Did you encounter any issues with online payments?</div>
            <div class="rating">
                <label><input type="radio" name="payments" value="1" required /> 1</label>
                <label><input type="radio" name="payments" value="2" /> 2</label>
                <label><input type="radio" name="payments" value="3" /> 3</label>
                <label><input type="radio" name="payments" value="4" /> 4</label>
                <label><input type="radio" name="payments" value="5" /> 5</label>
            </div>
        </div>

        <!-- Cleanliness -->
        <div class="question-group">
            <div class="question">How satisfied were you with the cleanliness of the cinema?</div>
            <div class="rating">
                <label><input type="radio" name="cleanliness" value="1" required /> 1</label>
                <label><input type="radio" name="cleanliness" value="2" /> 2</label>
                <label><input type="radio" name="cleanliness" value="3" /> 3</label>
                <label><input type="radio" name="cleanliness" value="4" /> 4</label>
                <label><input type="radio" name="cleanliness" value="5" /> 5</label>
            </div>
        </div>

        <!-- Sound & Picture Quality -->
        <div class="question-group">
            <div class="question">How would you rate the sound and picture quality?</div>
            <div class="rating">
                <label><input type="radio" name="quality" value="1" required /> 1</label>
                <label><input type="radio" name="quality" value="2" /> 2</label>
                <label><input type="radio" name="quality" value="3" /> 3</label>
                <label><input type="radio" name="quality" value="4" /> 4</label>
                <label><input type="radio" name="quality" value="5" /> 5</label>
            </div>
        </div>

        <!-- Staff Experience -->
        <div class="question-group">
            <div class="question">How was your experience with the staff?</div>
            <div class="rating">
                <label><input type="radio" name="staff" value="1" required /> 1</label>
                <label><input type="radio" name="staff" value="2" /> 2</label>
                <label><input type="radio" name="staff" value="3" /> 3</label>
                <label><input type="radio" name="staff" value="4" /> 4</label>
                <label><input type="radio" name="staff" value="5" /> 5</label>
            </div>
        </div>

        <!-- Suggestions -->
        <textarea name="suggestions"
                  placeholder="Do you have any suggestions for us to improve your experience?"
                  required></textarea>

        <button type="submit" class="submitBtn">Submit</button>
    </form>

    <!-- FOOTER -->
    <footer>
        <jsp:include page="/components/client-footer.jsp" />
    </footer>
</div>
</body>
</html>
