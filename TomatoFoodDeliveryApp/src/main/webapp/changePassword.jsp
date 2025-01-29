<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>


	<%@ page import="com.tomato.model.Cart"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/profile-forms.css">
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

    <main class="form-container">
        <div class="form-content">
            <div class="form-header">
                <div class="header-avatar">🔒</div>
                <h1>Change Password</h1>
            </div>
            <%
            	String message = (String)session.getAttribute("message");
            	Integer count = (Integer)request.getAttribute("count");
            	if(count!=null){
            	if(message!=null){
            		
            %>
				<p style="color: red;"><%=message%> <%=count%> attempts left to change for today</p><br>
			<%
            	}
            	}
			%>				
            <form class="profile-form" action="passwordServlet" method="post">
                <div class="form-group">
                    <label for="old-password">Old Password</label>
                    <input type="password" name="oldPassword" id="old-password" required>
                </div>

                <div class="form-group">
                    <label for="new-password">New Password</label>
                    <input type="password" name="newPassword" id="new-password" required>
                </div>
             <%
             	if(count==null){
             		if(message!=null){
             %>
					<p style="color: red;"><%=message%></p>
			 <%
            	 }
             	}
			 %>
                <div class="form-group">
                    <label for="confirm-password">Confirm New Password</label>
                    <input type="password" name="confirmPassword" id="confirm-password" required>
                </div>

                <button type="submit" class="save-button">Save Password</button>
            </form>
        </div>
    </main>
</body>
</html>