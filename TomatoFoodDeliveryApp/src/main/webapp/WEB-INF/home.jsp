<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.tomato.daoimplement.RestaurantDAOImplement"%>
<%@page import="com.tomato.model.Restaurant , java.util.List"%>
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
            
            
            <a href="orders.jsp">Orders</a>
            <a href="cart.jsp" class="cart-icon">🛒<span class="cart-count">0</span></a>
        </div>
    </nav>
   
    <main class="restaurants-grid">
     <%
          		RestaurantDAOImplement restaurantdao = new RestaurantDAOImplement();
                    	List<Restaurant> restaurantList= restaurantdao.getAllRestaurants();
                    	for(Restaurant r : restaurantList)
                    	{
          %>

    
        <div class="restaurant-card active">
        <a href="menuservlet?restaurantId=<%=r.getRestaurantId()%>">
        
            <img src="<%=r.getImagePath()%>" alt="<%=r.getName()%>">
          
            <div class="restaurant-info">
         
                <h2><%= r.getName()%></h2>
                <p class="address"><%=r.getAddress()%></p>
                <p class="cuisine"><%=r.getCuisineType()%></p>
                <div class="rating"><%=r.getRating()%> ⭐</div>
                <span class="status">
                <%
                if(r.getisActive())
                {
                	out.println("Active");
                }
               	else{
               		out.println("InActive");
               		} 
                %></span>
            </div>
            </a>
          </div>
     
            <%
                    	}
            %>
        
	</main>
    
</body>
</html>