<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  

	<%@page import="com.tomato.daoimplement.RestaurantDAOImplement"%>
	<%@page import="com.tomato.model.Restaurant , java.util.List, com.tomato.model.Cart"%>
	 
<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tomato - Food Delivery</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <nav>
        <div class="logo">Tomato</div>
        <div class="nav-links">
        
        <% 
        	if (session.getAttribute("user") != null) { %>
                    <div class="profile-dropdown">
                <span class="profile-icon">👤</span>
                <div class="dropdown-content">
                    <a href="profile.jsp">Profile</a>
                    <a href="orderServlet">Orders</a>
                    <a href="logoutServlet">Sign Out</a>
                </div>
            </div>
         <% }
        	
        	else { %>  
	            <a href="signIn.jsp?page=index">Sign In</a>
	            <a href="signup.jsp">Sign Up</a>
             <% 
             	} 
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
   
    <main class="restaurants-grid">
     <%
     			RestaurantDAOImplement restaurantdao = new RestaurantDAOImplement();
                    	List<Restaurant> restaurantList= restaurantdao.getAllRestaurants();
                      	for(Restaurant r : restaurantList)
                    	{
                      		String status = null;
                        	if(r.getisActive())
                            {
                            	 status = "Active";
                            }
                           	else{
                           		status = "InActive";
                           		}
                      		
          %>

    
        <div class="restaurant-card <%=r.getisActive()%>">
        <a href="menuservlet?restaurantId=<%=r.getRestaurantId()%>" style="text-decoration: none;">
            <img src="<%=r.getImagePath()%>" alt="<%=r.getName()%>">
            <div class="restaurant-info">
                <h2><%= r.getName() %></h2>
                
                <div class="rating"><%=r.getRating()%> ⭐</div>
                
                <p class="address"><%=r.getAddress()%></p>
                
                <p class="cuisine"><%=r.getCuisineType()%></p>
                
                <span class="status <%=status%>"><%=status%></span>
            </div>
            </a>
          </div>
     
            <%
                    	}
            %>
        
	</main>
    
</body>
</html>