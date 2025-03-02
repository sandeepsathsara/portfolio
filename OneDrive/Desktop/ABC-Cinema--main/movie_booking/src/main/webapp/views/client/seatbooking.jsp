<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Seat Booking - ${movie.title}</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(to bottom, #2a0a4a, #1a0632);
            color: white;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        /* Movie Info */
        .movie-info {
            display: flex;
            margin: 12px 0 30px 0;
            gap: 20px;
        }

        .movie-info img {
            width: 200px;
            border-radius: 10px;
        }

        .movie-details {
            flex: 1;
        }

        .movie-details h1 {
            font-size: 28px;
            margin-bottom: 10px;
        }

        .movie-details p {
            margin: 5px 0;
            margin-bottom: 12px;
        }

        /* Dropdowns + Next button */
        .dropdowns {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-bottom: 30px;
        }

        .dropdowns select {
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #6a0dad;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }

        .dropdowns .next-button {
            padding: 10px 20px;
            background-color: #FFD700;
            color: black;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        /* Seat & Purchase Sections */
        .seat-booking-container {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }

        /* Left side: seat details & purchase */
        .details-section {
            flex: 1;
            background-color: #42275A;
            padding: 20px;
            border-radius: 10px;
        }

        .details-section h1,
        .details-section h2 {
            color: #FFD700;
            margin: 0 0 15px 0;
        }

        .price-list {
            margin-bottom: 20px;
        }

        .price-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .selected-seats-list {
            margin-bottom: 20px;
        }

        /* Container that shows selected seats */
        #selectedSeats {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            color: #FFFFFF;
        }

        /* Individual selected seat badge */
        .selected-seat {
            background-color: #FFD700;
            color: #000000;
            padding: 5px 10px;
            border-radius: 5px;
            font-weight: bold;
            font-size: 14px; /* Increased from 2px to 14px for visibility */
        }

        /* The total price line */
        #totalPrice {
            margin-top: 10px;
            font-weight: bold;
            color: #FFD700;
            font-size: 1.2em;
        }

        .purchase-button {
            background-color: #FFD700;
            color: black;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            width: 100%;
            padding: 10px;
            cursor: pointer;
        }

        /* Right side: seat map */
        .seat-map-section {
            flex: 2;
            padding: 20px;
            background-color: #351A4E;
            border-radius: 10px;
        }

        .seat-map-section h2 {
            text-align: center;
            color: #FFD700;
            margin-bottom: 20px;
        }

        /* Hide seat map until Next is clicked */
        #seatMap {
            display: none;
        }

        .screen {
            background-color: #FFD700;
            height: 20px;
            width: 80%;
            margin: 0 auto 20px;
            text-align: center;
            border-radius: 5px;
            font-weight: bold;
            color: black;
            line-height: 20px;
        }

        .seat-map {
            display: grid;
            grid-template-columns: repeat(10, 1fr);
            gap: 10px;
            justify-content: center;
            margin: 0 auto;
        }

        .seat {
            width: 30px;
            height: 30px;
            background-size: cover;
            border-radius: 5px;
            cursor: pointer;
        }

        .normal { background-image: url('${pageContext.request.contextPath}/images/normal.png'); }
        .deluxe { background-image: url('${pageContext.request.contextPath}/images/dulax.png'); }
        .super  { background-image: url('${pageContext.request.contextPath}/images/high.png'); }
        .sold {
            background-image: url('${pageContext.request.contextPath}/images/disable.png');
            cursor: not-allowed;
        }
        .selected {
            outline: 2px solid #FFD700;
        }

        .legend {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin: 50px 0;
        }
        .legend-item {
            display: flex;
            align-items: center;
            gap: 5px;
            color: #FFF;
        }
        .legend-item img {
            width:1.5rem;
            height: 1.5rem;
        }
    </style>
</head>
<body>
<!-- Navbar -->
<nav>
    <jsp:include page="/components/client-navbar.jsp" />
</nav>

<div class="container">
    <!-- Movie Info -->
    <div class="movie-info">
        <img src="${pageContext.request.contextPath}/DBImages/${movie.imageUrl}" alt="${movie.title}">
        <div class="movie-details">
            <h1>${movie.title}</h1>
            <p><strong style="color:#FFD700">Description:</strong> ${movie.description}</p>
            <p><strong style="color:#FFD700">Language:</strong> ${movie.language}</p>
            <p><strong style="color:#FFD700">Age Range:</strong> ${movie.ageRange}</p>
            <p><strong style="color:#FFD700">Rating:</strong> ★ ${movie.rating}</p>
            <p><strong style="color:#FFD700">Price:</strong> Rs. ${movie.price}</p>
        </div>
    </div>

    <!-- Dropdowns -->
    <div class="dropdowns">
        <select id="date">
            <c:forEach var="showtime" items="${showtimes}">
                <option value="${showtime.date}">${showtime.date}</option>
            </c:forEach>
        </select>
        <select id="time">
            <c:forEach var="showtime" items="${showtimes}">
                <option value="${showtime.time}">${showtime.time}</option>
            </c:forEach>
        </select>
        <select id="theater">
            <c:forEach var="showtime" items="${showtimes}">
                <option value="${showtime.theater}">${showtime.theater}</option>
            </c:forEach>
        </select>
        <button class="next-button" id="nextButton">Next</button>
    </div>

    <!-- Seat Booking & Purchase Section -->
    <div class="seat-booking-container">
        <!-- Left Section: Seat Details -->
        <div class="details-section">
            <h1>Seat Booking</h1>

            <div class="price-list">
                <h2>Seat Prices</h2>
                <div class="price-item">
                    <span>Normal</span><span>Rs. 200</span>
                </div>
                <div class="price-item">
                    <span>Deluxe</span><span>Rs. 250</span>
                </div>
                <div class="price-item">
                    <span>Super</span><span>Rs. 300</span>
                </div>
            </div>

            <div class="selected-seats-list">
                <h2>Your Selected Seats</h2>
                <div id="selectedSeats">
                    <span>No seats selected</span>
                </div>
                <div class="total" id="totalPrice">Total: Rs. 0</div>
            </div>

            <!-- Purchase button -->
            <button type="button" class="purchase-button" id="purchaseButton">Purchase</button>
        </div>

        <!-- Right Section: Seat Map -->
        <div class="seat-map-section">
            <h2>Select Your Seats</h2>

            <div class="legend">
                <div class="legend-item">
                    <img src="${pageContext.request.contextPath}/images/normal.png" alt="img">Normal
                </div>
                <div class="legend-item">
                    <img src="${pageContext.request.contextPath}/images/dulax.png" alt="img">Deluxe
                </div>
                <div class="legend-item">
                    <img src="${pageContext.request.contextPath}/images/high.png" alt="img">Super
                </div>
                <div class="legend-item">
                    <img src="${pageContext.request.contextPath}/images/disable.png" alt="img">Sold
                </div>
            </div>

            <div class="screen">Screen this way</div>
            <!-- Seat map grid (initially hidden) -->
            <div class="seat-map" id="seatMap"></div>
        </div>
    </div>

    <!-- Form for Submission -->
    <form id="bookingForm" action="${pageContext.request.contextPath}/make-checkout" method="POST">
        <input type="hidden" name="action" value="make-checkout">
        <input type="hidden" name="movieName" value="${movie.title}">
        <input type="hidden" name="movieId" value="${movie.id}">
        <input type="hidden" name="showId" id="showIdField">
        <input type="hidden" name="showDate" id="showDateField">
        <input type="hidden" name="showTime" id="showTimeField">
        <input type="hidden" name="selectedSeats" id="selectedSeatsField">
        <input type="hidden" name="totalPrice" id="totalPriceField">
    </form>
</div>

<!-- Footer -->
<footer>
    <jsp:include page="/components/client-footer.jsp" />
</footer>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const seatMapElement     = document.getElementById('seatMap');
        const selectedSeatsDiv   = document.getElementById('selectedSeats');
        const totalPriceDiv      = document.getElementById('totalPrice');
        const nextButton         = document.getElementById('nextButton');
        const purchaseButton     = document.getElementById('purchaseButton');
        const form               = document.getElementById('bookingForm');

        // Hidden form fields
        const showIdField        = document.getElementById('showIdField');
        const showDateField      = document.getElementById('showDateField');
        const showTimeField      = document.getElementById('showTimeField');
        const selectedSeatsField = document.getElementById('selectedSeatsField');
        const totalPriceField    = document.getElementById('totalPriceField');

        // Dropdown elements
        const dateDropdown       = document.getElementById('date');
        const timeDropdown       = document.getElementById('time');
        const theaterDropdown    = document.getElementById('theater');

        // Pricing
        const seatPrices = {
            normal: 200,
            deluxe: 250,
            super:  300
        };

        let selectedSeats = []; // Will store objects like { seatNumber: "A1", seatPrice: 200 }

        // Generate seat map
        function generateSeatMap() {
            const rows = ['A', 'B', 'C', 'D', 'E', 'F'];
            // row 0-1 => normal, 2-3 => deluxe, 4-5 => super
            const seatTypes = ['normal', 'normal', 'deluxe', 'deluxe', 'super', 'super'];

            rows.forEach(function(row, rowIndex) {
                for (let i = 1; i <= 10; i++) {
                    const seat       = document.createElement('div');
                    const seatType   = seatTypes[rowIndex];
                    const seatPrice  = seatPrices[seatType];

                    // Set a data attribute for seatNumber, e.g. "A1"
                    seat.dataset.seatNumber = row + i;
                    seat.dataset.seatPrice  = seatPrice;

                    // Add classes
                    seat.classList.add('seat', seatType);

                    // On click => add/remove from selectedSeats
                    seat.addEventListener('click', function() {
                        if (this.classList.contains('sold')) return; // ignore sold seats

                        this.classList.toggle('selected');
                        if (this.classList.contains('selected')) {
                            selectedSeats.push({
                                seatNumber: this.dataset.seatNumber,
                                seatPrice:  parseInt(this.dataset.seatPrice, 10)
                            });
                        } else {
                            selectedSeats = selectedSeats.filter(function(s) {
                                return s.seatNumber !== this.dataset.seatNumber;
                            }, this);
                        }
                        renderSelectedSeats();
                    });

                    seatMapElement.appendChild(seat);
                }
            });
        }

        // Renders selected seats and total price
        function renderSelectedSeats() {
            // If no seats, show placeholder
            if (selectedSeats.length === 0) {
                selectedSeatsDiv.innerHTML = '<span>No seats selected</span>';
            } else {
                // Build seat labels by string concatenation
                const seatLabels = selectedSeats.map(function(s) {
                    return '<span class="selected-seat">' + s.seatNumber + '</span>';
                }).join('');
                selectedSeatsDiv.innerHTML = seatLabels;
            }

            // Calculate sum of seatPrices
            const sum = selectedSeats.reduce(function(acc, s) {
                return acc + s.seatPrice;
            }, 0);
            totalPriceDiv.textContent = 'Total: Rs. ' + sum;
        }

        // Show seat map after Next is clicked
        nextButton.addEventListener('click', function() {
            seatMapElement.style.display = 'grid';

            // Fill hidden fields
            const selectedDate = dateDropdown.value;
            const selectedTime = timeDropdown.value;
            const selectedTheater = theaterDropdown.value;

            showDateField.value = selectedDate;
            showTimeField.value = selectedTime;
            showIdField.value = selectedTheater;

            // Scroll to seat map
            seatMapElement.scrollIntoView({ behavior: 'smooth' });
        });

        // On Purchase => set hidden fields and submit form
        purchaseButton.addEventListener('click', function() {
            const totalAmount = selectedSeats.reduce(function(acc, s) {
                return acc + s.seatPrice;
            }, 0);

            const seatNamesCSV = selectedSeats.map(function(s) {
                return s.seatNumber;
            }).join(',');

            selectedSeatsField.value = seatNamesCSV;
            totalPriceField.value    = totalAmount;

            // Ensure date and time are set
            if (!showDateField.value || !showTimeField.value) {
                alert('Please select a date and time before proceeding.');
                return;
            }

            form.submit();
        });

        // Generate map initially (but hidden until Next)
        generateSeatMap();
    });

</script>

</body>
</html>




