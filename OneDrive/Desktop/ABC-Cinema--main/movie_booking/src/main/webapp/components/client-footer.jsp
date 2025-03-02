<style>
    footer {
        position: relative;
        background-color: rgba(42, 0, 102, 1);
        color: #FFFFFF;
        overflow: hidden;
    }

    footer::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.1); /* Adjust opacity here */
        z-index: 1;
    }

    .footer-content {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        padding: 30px;
        position: relative;
        z-index: 2; /* Ensures content is above overlay */
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
        position: relative;
        z-index: 2;
    }

    .social-icons {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        position: relative;
        z-index: 2;
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

    hr {
        border: 0;
        height: 1px;
        background-color: rgba(255, 255, 255, 0.2);
        margin: 30px 0;
        position: relative;
        z-index: 2;
    }

    .copyright {
        text-align: center;
        padding: 20px 10px;
        position: relative;
        z-index: 2;
    }

</style>
<footer>
    <div class="footer-content">
        <div class="footer-logo">
            <img src="https://i.ibb.co/XDWNCbt/image.png" alt="ABC Cinema logo"/>
            <span>ABC</span> CINEMA
        </div>
        <div class="footer-links">
            <a href="<c:url value='/about-us'/>">About us</a>
            <a href="<c:url value='/contact-us'/>">Contact us</a>
            <a href="<c:url value='/privacy-policy'/>">Privacy Policy</a>
        </div>
        <div class="social-icons">
            <span>Follow us:</span>
            <div class="social-icons-container">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
            </div>
        </div>
    </div>
    <hr>
    <div class="copyright">
        © 2024 ABC Cinema. All rights reserved.
    </div>
</footer>
