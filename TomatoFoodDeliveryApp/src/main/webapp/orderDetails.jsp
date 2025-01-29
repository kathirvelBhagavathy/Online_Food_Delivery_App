<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

	<%@page import="com.tomato.daoimplement.MenuDAOImplement"%>
	<%@page import="com.tomato.daoimplement.RestaurantDAOImplement"%>
	<%@page import="com.tomato.daoimplement.OrderDAOImplent"%>
	<%@page import="com.tomato.daoimplement.OrderItemDAOImplement, com.tomato.model.OrderItem,com.tomato.model.Order,
					com.tomato.model.Restaurant,com.tomato.model.Menu,com.tomato.model.Cart, java.util.List"%>
					
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/orderDetails.css">
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

	<%
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		OrderItemDAOImplement orderItemDao = new OrderItemDAOImplement();
		List<OrderItem> orderItems = orderItemDao.getAllOrderItemsByOrder(orderId);
		OrderDAOImplent orderDao = new OrderDAOImplent();
		Order order = orderDao.getOrder(orderId);
		int restaurantId = order.getRestaurantId();
		RestaurantDAOImplement restaurantDao = new RestaurantDAOImplement();
		Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);
	%>
    <main class="order-details-container">
        <div class="order-header">
            <h1 class="order-id">Order #<%=orderId%></h1>
            <div class="order-meta">
                <div class="restaurant-id">Restaurant: <%=restaurant.getName()%></div>
                
                <div class="order-date"><%=order.getOrderDate()%></div>
            </div>
        </div>
        <div class="order-items">
            <table class="items-table">
                <thead>
                    <tr>
                        <th>Item</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                <%
					     for(OrderItem orderItem : orderItems)
						{
							MenuDAOImplement menuDao = new MenuDAOImplement();
							Menu menu = menuDao.getMenu(orderItem.getMenuId());
				    %>
                
                    <tr>
                    
                        <td><%=menu.getItemName()%></td>
                        <td>
                            <img src="<%=menu.getImagePath()%>" 
                                 alt="<%=menu.getItemName()%>" 
                                 class="menu-image">
                        </td>
                        <td><%=orderItem.getQuantity()%></td>
                        <td>₹<%=menu.getPrice()%></td>
                        <td>₹<%=orderItem.getTotalPrice()%></td>
                       
                    </tr>
                     <%
							}
				    	%>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4">Subtotal</td>
                        <td>₹<%=order.getTotalAmount()%></td>
                    </tr>
                    <tr>
                        <td colspan="4">Delivery Fee</td>
                        <td>₹40</td>
                    </tr>
                    <tr>
                        <td colspan="4">Taxes</td>
                        <td>₹<%=(int)(order.getTotalAmount()*0.05) %></td>
                    </tr>
                    <tr class="total-row">
                        <td colspan="4">Total Amount</td>
                        <td>₹<%=(int)(order.getTotalAmount()+40+order.getTotalAmount()*0.05)%></td>
                    </tr>
                </tfoot>
            </table>
        </div>
        
    </main>
    
</body>
</html>