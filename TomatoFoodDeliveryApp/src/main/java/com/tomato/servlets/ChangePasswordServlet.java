package com.tomato.servlets;

import java.io.IOException;

import com.tomato.daoimplement.UserDAOImplement;
import com.tomato.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/passwordServlet")
public class ChangePasswordServlet extends HttpServlet{
	int count = 3;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		if(oldPassword.equals(user.getPassword())) {
			if(newPassword.equals(confirmPassword)) {
				user.setPassword(newPassword);
				UserDAOImplement userDao = new UserDAOImplement();
				int result = userDao.updatePassword(user);
				if(result<=0) {
					resp.sendRedirect("error.jsp");
				}
				else {
					resp.sendRedirect("success.jsp");
				}
			}
			else {
				session.setAttribute("message", "Password MisMatch");
				resp.sendRedirect("changePassword.jsp");
			}
		}
		else {
			count--;
			if(count>1) {
			session.setAttribute("message", "Wrong password");
			req.setAttribute("count",count);
			RequestDispatcher rd = req.getRequestDispatcher("changePassword.jsp");
			rd.forward(req, resp);
			}
			else {
//				resp.sendRedirect("error.jsp");
				
				System.out.println(count);
				System.out.println("count gone below 0");
			}
		}
	}
}
