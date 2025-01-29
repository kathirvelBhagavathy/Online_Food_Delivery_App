<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <%@ page import="com.tomato.model.Cart, com.tomato.model.User"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/profile.css">
</head>
<body>
    <nav>
        <div class="logo">Tomato</div>
        <div class="nav-links">
            <a href="index.jsp">Home</a>
            <div class="profile-dropdown">
                <span class="profile-icon">👤</span>
                <div class="dropdown-content">
                    <a href="#" class="disabled">Profile</a>
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

    <main class="profile-container">
        <div class="profile-content">
            <div class="profile-header">
                <div class="profile-avatar">👤</div>
                <h1>User Profile</h1>
            </div>

			<%
				User user = (User)session.getAttribute("user");
			%>
            <div class="profile-details">
                <div class="detail-group">
                    <div class="detail-item">
                        <label>Name</label>
                        <p><%=user.getName()%></p>
                    </div>
                    <div class="detail-item">
                        <label>Username</label>
                        <p><%=user.getUsername()%></p>
                    </div>
                </div>

                <div class="detail-group">
                    <div class="detail-item">
                        <label>Email</label>
                        <p><%=user.getEmail()%></p>
                    </div>
                    <div class="detail-item">
                        <label>Phone</label>
                        <p><%=user.getPhone()%></p>
                    </div>
                </div>

                <div class="detail-item full-width">
                    <label>Address</label>
                    <p><%=user.getAddress()%></p>
                </div>

                <div class="detail-group">
                    <div class="detail-item">
                        <label>Created Date</label>
                        <p><%=user.getCreatedDate()%></p>
                    </div>

                <div class="detail-item">
                    <label>Last Login</label>
                    <p><%=user.getLastLoginDate()%></p>
                </div>
                </div>
            </div>

            <div class="profile-actions">
                <button class="edit-profile-btn" onclick="window.location.href='editProfile.jsp'">Edit Profile</button>
                <button class="change-password-btn" onclick="window.location.href='changePassword.jsp'">Change Password</button>
            </div>
        </div>
    </main>
</body>
</html>
