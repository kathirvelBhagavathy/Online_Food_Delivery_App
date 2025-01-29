package com.tomato.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.tomato.daoimplement.UserDAOImplement;
import com.tomato.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserDAOImplement userDao = new UserDAOImplement();
		User user = userDao.getUser(username);
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		
		if(user==null)
		{
			PrintWriter out = resp.getWriter();
			session.setAttribute("loginError", "Wrong Username");
			resp.sendRedirect("signIn.jsp");
		}
		
		else {
			
			if(password.equals(user.getPassword())) {
			String page = req.getParameter("page");
			if(page.equals("index")) {
				RequestDispatcher r= req.getRequestDispatcher("index.jsp");
				r.forward(req, resp);
			}
			else if(page.equals("cart")) {
				RequestDispatcher r= req.getRequestDispatcher("cart.jsp");
				r.forward(req, resp);
			}
			else {
				RequestDispatcher r= req.getRequestDispatcher(page);
				r.forward(req, resp);
			}
			System.out.println("Password matches");
			}
			else {
				session.setAttribute("loginError", "Wrong Password");
				resp.sendRedirect("signIn.jsp");
			}
		}
	}
}
