<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>

    <%@ page import="com.tomato.model.User" %>
    
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Sign In - Tomato</title>
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
	     
	        <form class="auth-form" action="LoginServlet" method="post">
	        <%
	    	String message = (String)session.getAttribute("loginError");
	    		if(message!=null)
	    		{
	     %>	
	     	<p style="color: white;"><%= message %></p>
	     <% 	
	    		}
	    		String page1=(String)request.getParameter("page");
	    	%>
	            <h2>Sign In</h2>
	            <input type="text" placeholder="Username" name="username" required>
	            <input type="password" placeholder="Password" name="password" required>
	            <input type="hidden" name="page" value="<%=page1%>">
	            <button type="submit">Sign In</button>
	            <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
	        </form>
	    </div>
	</body>
</html>