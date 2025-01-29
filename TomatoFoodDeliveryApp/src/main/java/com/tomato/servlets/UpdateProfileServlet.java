package com.tomato.servlets;

import java.io.IOException;

import com.tomato.daoimplement.UserDAOImplement;
import com.tomato.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		String name = req.getParameter("name");
		int userId = Integer.parseInt(req.getParameter("userId"));
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		 user.setName(name);
		 user.setEmail(email);
		 user.setPhone(phone);
		 user.setAddress(address);
		 UserDAOImplement userDao = new UserDAOImplement();
		 int result = userDao.updateUser(user);
		 if(result>0) {
			 resp.sendRedirect("success.jsp");
		 }
		 else {
			 resp.sendRedirect("error.jsp");
		 }
		 
	}
}
