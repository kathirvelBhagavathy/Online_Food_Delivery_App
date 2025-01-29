<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>   
 
    <%@page import="com.tomato.daoimplement.RestaurantDAOImplement"%>
	<%@page import="com.tomato.model.Restaurant , java.util.List , 
				com.tomato.model.Menu, com.tomato.model.Cart"%>
				
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Menu</title>
	    <link rel="stylesheet" href="css/common.css">
	    <link rel="stylesheet" href="css/menu.css">
	</head>
	<body>
	<%
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		RestaurantDAOImplement restaurant = new RestaurantDAOImplement();
		Restaurant r = restaurant.getRestaurant(restaurantId);
		String menuPage = "menuservlet?restaurantId="+restaurantId;
	%>
	    <nav>
	        <div class="logo">Tomato</div>
	        <div class="nav-links">
	        <a href="index.jsp">Home</a>
	          <% if (session.getAttribute("user") != null) { %>
                    <div class="profile-dropdown">
                <span class="profile-icon">👤</span>
                <div class="dropdown-content">
                    <a href="profile.html">Profile</a>
                    <a href="orderServlet">Orders</a>
                    <a href="logoutServlet">Sign Out</a>
                </div>
            </div>
         <% } else { %> 
            <a href="signIn.jsp?page=menuservlet?restaurantId=<%=r.getRestaurantId()%>">Sign In</a>
            <a href="signup.jsp">Sign Up</a>
             <% }
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
	
	    <div class="restaurant-header">
	        <img src="<%=r.getImagePath()%>" alt="<%=r.getName()%>">
	        <div class="restaurant-details">
	            <h1><%=r.getName()%></h1>
	            <p class="cuisine"><%=r.getCuisineType()%></p>
	            <div class="rating"><%=r.getRating()%>⭐</div>
	        </div>
	    </div>
	    
	    <main class="menu-container">
		 <section class="menu-section">
	
	<%
		List<Menu> menuList=(List<Menu>)request.getAttribute("menuList");
		for(Menu m : menuList)
		{
	%>

	        
	            <div class="menu-item">
	                <img src="<%=m.getImagePath()%>" alt="<%=m.getItemName()%>">
	                <div class="item-details">
	                    <h3><%=m.getItemName() %></h3>
	                    <p><%=m.getDescription()%></p>
	                    <div class="item-meta">
	                        <span class="price">₹<%=m.getPrice()%></span>
	                        <span class="rating"><%=m.getRating() %> ⭐</span>
	                    </div>
	                    <span class="availability <%=m.getIsAvailable()?"available":"unavailable"%>"><%if(m.getIsAvailable())
	                    									{out.println("Available");}
	                    									else{out.println("Not Available");}%></span>
	                </div>
	                <div class="cart-actions">
	                    <form class="auth-form" action="cartServlet" method="post">
	                    <input type="hidden" name ="action" value="add">
	                    <input type="hidden"  name = "restaurantId" value=<%=m.getRestaurantId()%>>
            			<input type="hidden" name ="itemId" value=<%=m.getMenuId()%>>
            			<input type="number" min="1" max="10" value="1" name = "quantity" class="quantity" >
            			
            			<script>
						    document.querySelector(".quantity").addEventListener("keydown", function (e) {
						        e.preventDefault(); 
						    });
						</script>
	                    <button class="add-to-cart" >Add to Cart</button>
	                    </form>
	                </div>
	            </div>
	            
	 <%
		}
	 %>
	  </section>
	  </main>
	
	</body>
	</html>