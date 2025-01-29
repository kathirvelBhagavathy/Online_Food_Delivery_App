	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

    <%@page import="com.tomato.daoimplement.RestaurantDAOImplement"%>
    <%@ page import="com.tomato.model.Cart, com.tomato.model.CartItem,com.tomato.model.Restaurant, java.util.List" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/checkout.css">
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
            <%Cart cart = (Cart)session.getAttribute("cart"); %>
            <a href="cart.jsp" class="cart-icon">🛒<span class="cart-count"><%=cart.getCartItems().size()%></span></a>
        </div>
    </nav>

    <main class="checkout-container">
        <div class="checkout-left">
        <form action="addOrderServlet">
            <section class="checkout-section">
                <h2 class="section-title">Delivery Address</h2>
                <textarea 
                	name="address"
                    class="address-input" 
                    placeholder="Enter your complete delivery address..."
                    rows="5"
                    required
                ></textarea>
            </section>
		
            <section class="checkout-section">
                <h2 class="section-title">Payment Method</h2>
                
                <select name ="payment" id="paymentMethod" class="payment-select" required>
                    <option value="">Select Payment Method</option>
                    <option value="COD">Cash on Delivery</option>
                    <option value="UPI">UPI</option>
                    <option value="card">Debit Card</option>
                    <option value="card">Credit Card</option>
                </select>
            </section>
        </div>
       			 
        <div class="checkout-right">
            <section class="checkout-section">
                <h2 class="section-title">Order Summary</h2>
                 <%
 					 	
	                   	 List<CartItem> cartItems = cart.getCartItems();
	                   	 RestaurantDAOImplement restaurantDao = new RestaurantDAOImplement();
	                   	 Restaurant restaurant = restaurantDao.getRestaurant(cart.getRestaurantId());
	                    if(cart!=null && !cart.getCartItems().isEmpty())
	                    {
	                    	for(CartItem c : cartItems)
	                    	{
                    %>
                <div class="cart-items">
                    <div class="cart-item">
                        <div class="item-details">
                            <div class="item-name"><%=c.getItemName()%></div>
                            <div class="restaurant-name"><%=restaurant.getName()%></div>
                        </div>
                        <div class="quantity">x<%=c.getQuantity() %></div>
                        <div class="item-price">₹<%=c.getPrice()*c.getQuantity()%></div>
                    </div>
                   
                </div>
                 <%
                    	}
	                    }
                    %>

                <div class="price-breakdown">
                    <div class="price-row">
                        <span>Subtotal</span>
                        <span>₹<%=cart.totalAmount()%></span>
                    </div>
                    <div class="price-row">
                        <span>Delivery Fee</span>
                        <span>₹40</span>
                    </div>
                    <div class="price-row">
                        <span>Taxes</span>
                        <span>₹<%=cart.totalAmount()*0.05%></span>
                    </div>
                    <div class="price-row total-row">
                        <span>Total</span>
                        <span>₹<%=cart.totalAmount()+40+cart.totalAmount()*0.05%></span>
                    </div>
                </div>

                <button type="submit" class="checkout-btn">Place Order</button>
            </section>
        </div>
        </form>
    </main>
</body>
</html>