// Set up Stripe.js and Elements to use in checkout form
var stripe = Stripe('pk_test_51QWv90HGW1CG3lRCYRH81MFVkKaSwGGgopT17X0UWq7v9EegdfEv8LZxlEigsBPF2ou0RpSZKgVjsWY32pcHuAw000WJ3UrPcg'); // Replace with your actual publishable key from Stripe
var elements = stripe.elements();

// Custom styling can be passed to options when creating an Element
var style = {
    base: {
        color: "#32325d",
        // Add more styling here if needed
    }
};

// Create an instance of the card Element and mount it to the DOM
var card = elements.create('card', {style: style});
card.mount('#card-element');

// Handle real-time validation errors from the card Element
card.addEventListener('change', function(event) {
    var displayError = document.getElementById('card-errors');
    if (event.error) {
        displayError.textContent = event.error.message;
    } else {
        displayError.textContent = '';
    }
});

// Handle form submission
var form = document.getElementById('payment-form');
form.addEventListener('submit', function(event) {
    event.preventDefault();

    // Disable the submit button to prevent repeated clicks
    document.getElementById('submit-button').disabled = true;

    stripe.createToken(card).then(function(result) {
        if (result.error) {
            // Inform the user if there was an error
            var errorElement = document.getElementById('card-errors');
            errorElement.textContent = result.error.message;
            // Re-enable the submit button
            document.getElementById('submit-button').disabled = false;
        } else {
            // Send the token to your server
            stripeTokenHandler(result.token);
        }
    });
});

// Submit the form with the Stripe Token
function stripeTokenHandler(token) {
    var form = document.getElementById('payment-form');
    var hiddenInput = document.createElement('input');
    hiddenInput.setAttribute('type', 'hidden');
    hiddenInput.setAttribute('name', 'stripeToken');
    hiddenInput.setAttribute('value', token.id);
    form.appendChild(hiddenInput);

    // Submit the form
    form.submit();
}
