<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@ page import="com.tomato.model.Cart, com.tomato.model.Order, java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/orders.css">
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
                    <a href="#" class="disabled">Orders</a>
                    <a href="signin.html">Sign Out</a>
                </div>
            </div>
            <%
            Cart cart = (Cart)session.getAttribute("cart");
	          	if(cart==null)
	          	{
          		%>
	            	<a href="cart.jsp" class="cart-icon">🛒<span class="cart-count">0</span></a>
	            <%
	          	} else{
	          		%>
	          		<a href="cart.jsp" class="cart-icon">🛒<span class="cart-count"><%=cart.getCartItems().size()%></span></a>				
	          <%
	          	}
	            %>
        </div>
    </nav>

    <main class="orders-container">
        <h1>My Orders</h1>
        <%
        	List<Order> ordersList = (List<Order>)request.getAttribute("ordersList");
        	for(Order o : ordersList)
        	{
        %>
        
        <a href="orderDetails.jsp?orderId=<%=o.getOrderId()%>" class="order-card">
            <div class="order-info">
                <div class="order-id">Order ID: #<%=o.getOrderId()%></div>
                <div class="restaurant-id">Restaurant Id: Rest<%=o.getRestaurantId()%></div>
                <div class="order-date"><%=o.getOrderDate()%></div>
                <div class="order-total">Total Amount: ₹<%=o.getTotalAmount() %></div>
                <div class="order-meta">
                    <div class="order-status status-delivered"><%=o.getStatus()%></div>
                    <div class="payment-mode">Paid via <%=o.getPaymentMode()%></div>
                </div>
            </div>
        </a>
        <%
        	}
        %>

    </main>
</body>
</html>