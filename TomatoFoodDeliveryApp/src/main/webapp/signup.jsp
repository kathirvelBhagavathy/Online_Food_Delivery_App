<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Tomato</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/auth.css">
</head>
<body>
    <nav>
        <div class="logo">Tomato</div>
        <div class="nav-links">
            <a href="index.jsp">Home</a>
        </div>
    </nav>

    <div class="auth-container">
        <form class="auth-form" action="signupServlet" method="post">
        <%
        	String message = (String)session.getAttribute("message");
        	if(message!=null){
        %>
        	<h3 style= "color:white;"><%=message%></h3>
        <%
        	}
        %>
            <h2>Sign Up</h2>
            <input type="text" placeholder="Full Name" name = "fullname" required>
            <input type="text" placeholder="Username" name = "username" required>
            <input type="email" placeholder="Email" name = "email" required>
            <input type="tel" placeholder="Phone Number" name = "phone" required>
            <textarea placeholder="Address" name = "address" required></textarea>
            <select name="role" required>
                <option value="">Select Role</option>
                <option value="customer">User</option>
                <option value="restaurant">Restaurant User</option>
                <option value="delivery">Delivery Partner</option>
            </select>
            <input type="password" placeholder="Password" name = "password" required>
           
            <button type="submit">Sign Up</button>
            <p>Already have an account? <a href="signIn.jsp">Sign In</a></p>
        </form>
    </div>
</body>
</html>