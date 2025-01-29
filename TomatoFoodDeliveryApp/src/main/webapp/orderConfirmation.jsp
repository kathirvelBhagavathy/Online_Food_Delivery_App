<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/orderConfirmation.css">
</head>
<body>
    <nav>
        <div class="logo">Tomato</div>
        <div class="nav-links">
            <a href="index.jsp">Home</a>
            <div class="profile-dropdown">
                <span class="profile-icon">👤</span>
                <div class="dropdown-content">
                    <a href="profile.jsp">Profile</a>
                    <a href="orderServlet">Orders</a>
                    <a href="logoutServlet">Sign Out</a>
                </div>
            </div>
        </div>
    </nav>

    <main class="confirmation-container">
        <div class="confirmation-content">
            <div class="status-message">
                <h1>Order Confirmed!</h1>
                <p class="status">Your order is on the way</p>
                <p class="order-id">Order ID: #<%=(Integer)session.getAttribute("orderId")%></p>
            </div>

            <div class="animation-container">
                <div class="road"></div>
                <div class="bike-container">
                    <div class="bike">🛵</div>
                </div>
            </div>

            <a href="index.jsp" class="home-button">Return to Home</a>
        </div>
    </main>
</body>
</html>