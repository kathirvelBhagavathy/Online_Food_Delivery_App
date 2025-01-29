package com.tomato.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

import com.tomato.daoimplement.UserDAOImplement;
import com.tomato.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/signupServlet")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("fullname");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String role;
		if(req.getParameter("role").equals("customer")) {
			role = "User";
		}
		else if(req.getParameter("role").equals("restaurant")) {
			 role = "RestuarantUser";
			}
		else if(req.getParameter("role").equals("delivery")) {
			 role = "DeliveryAgent";
			}
		else {
			role = "AdminUser";
		}
		String address = req.getParameter("address");
		Date date = Date.valueOf(LocalDate.now());
		
		System.out.println(date);
		System.out.println(role);
		
		UserDAOImplement userDao = new UserDAOImplement();
		User user = userDao.getUser(username);
		HttpSession session = req.getSession();
		if(user!=null) {
			session.setAttribute("message", "Username already exists, Please try with different username");
			System.out.println("username already exists");
			resp.sendRedirect("signup.jsp");
		}
		else {
			user = new User(name, username, password, email, phone, address, role, date);
			userDao.addUser(user);
			session.setAttribute("user", user);
			resp.sendRedirect("home.jsp");
		}
	}

}
