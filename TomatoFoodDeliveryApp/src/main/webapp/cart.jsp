<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

    <%@page import="com.tomato.model.Cart, com.tomato.daoimplement.RestaurantDAOImplement,
    				com.tomato.model.Restaurant, com.tomato.model.CartItem, com.tomato.model.User"%>
    <%@page import="java.util.List"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/cart.css">
</head>
<body>
    <nav>
        <div class="logo">Tomato</div>
        <div class="nav-links">
        <a href="index.jsp">Home</a>
         <% 
         if (session.getAttribute("user") != null) { %>
            <div class="profile-dropdown">
                <span class="profile-icon">👤</span>
                <div class="dropdown-content">
                    <a href="profile.jsp">Profile</a>
                    <a href="orders.jsp">Orders</a>
                    <a href="logoutServlet">Sign Out</a>
                </div>
            </div>
            <% } else { %>   
	            <a href="signIn.jsp?page=cart">Sign In</a>
	            <a href="signup.jsp">Sign Up</a>
             <% } 
         		Cart cart = (Cart)session.getAttribute("cart");
             if(cart==null){
             %>
            <a href="#" class="cart-icon">🛒<span class="cart-count">0</span></a>
             </div>
    </nav>
    <main class="cart-container">
        <span class="nocart"><h1>Your Cart is empty!</h1></span>
        </main>
            <% } 
             else {
             %>
            <a href="#" class="cart-icon">🛒<span class="cart-count"><%=cart.getCartItems().size()%></span></a>
            
             
        </div>
    </nav>

    <main class="cart-container">
        <h1>Your Cart</h1>

        <div class="cart-content">
            <div class="cart-items">
                <div class="restaurant-group">
                <%
                
                	int restaurantId = cart.getRestaurantId();
                	RestaurantDAOImplement restaurant = new RestaurantDAOImplement();
            		Restaurant r = restaurant.getRestaurant(restaurantId);
                %>
                    <h2 class="restaurant-name"><%=r.getName()%></h2>
                    <%
                    List<CartItem> cartItems = cart.getCartItems();
                    if(cart!=null && !cart.getCartItems().isEmpty())
                    {
                    	for(CartItem c : cartItems)
                    	{
                    %>
                    <div class="cart-item">
                        <img src="<%=c.getImagePath()%>" 
                             alt="Butter Chicken" 
                             class="item-image">
                        <div class="item-details">
                            <h3><%=c.getItemName()%></h3>
                            <p class="item-price">₹<%=c.getPrice()%></p>
                        </div>
                        <div class="quantity-controls">
                        <form action="cartServlet" method="post">
                        	<input type="hidden" name="itemId" value="<%=c.getId()%>">
                        	<input type="hidden" name="action" value="update">
                        	<input type="hidden" name="restaurantId" value="<%=restaurantId %>">
                        	<input type="hidden" name="quantity" value="<%=c.getQuantity()-1%>">
                            <button class="quantity-btn">-</button>
                            </form>
                            <span class="quantity"><%=c.getQuantity()%></span>
                            <form action="cartServlet" method="post">
                        	<input type="hidden" name="itemId" value="<%=c.getId()%>">
                        	<input type="hidden" name="action" value="update">
                        	<input type="hidden" name="restaurantId" value="<%=restaurantId %>">
                        	<input type="hidden" name="quantity" value="<%=c.getQuantity()+1%>">
                            <button class="quantity-btn">+</button>
                            </form>
                        </div>
                        <p class="item-total">₹<%=(c.getPrice()*c.getQuantity())%></p>
                        <form action="cartServlet" method="post">
                        	<input type="hidden" name="restaurantId" value="<%=restaurantId %>">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="itemId" value="<%=c.getId()%>">
                        <button class="remove-item">✕</button>
                        </form>
                    </div>
                    <%
                    	}
                    }
                    %>
                    
            <div class="cart-summary">
                <h2>Order Summary</h2>
                <div class="summary-item">
                    <span>Items Subtotal</span>
                    <span>₹<%=cart.totalAmount()%></span>
                </div>
                
                <div class="summary-item total">
                    <span>Total Amount</span>
                    <span>₹<%=cart.totalAmount()%></span>
                </div>
                <button class="checkout-btn-to-menu" onclick="window.location.href='menuservlet?restaurantId=<%=r.getRestaurantId()%>'">
                    Add More Items
                </button>
                <%
                User user = (User)session.getAttribute("user");
                	if(user==null)
                	{
                %>
                <button class="checkout-btn" onclick="window.location.href='signIn.jsp?page=cart'">
                    Proceed to Checkout
                </button>
                <%
                	} else 
                		{
                %>
                		<button class="checkout-btn" onclick="window.location.href='checkout.jsp'">
                        Proceed to Checkout
                    </button>
                <%
                		}
                %>
            </div>
        </div>
    </main>
    			<%
                    } 
             	%>
</body>
</html>