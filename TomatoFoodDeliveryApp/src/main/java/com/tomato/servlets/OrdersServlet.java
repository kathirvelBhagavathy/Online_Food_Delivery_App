package com.tomato.servlets;

import java.io.IOException;
import java.util.List;

import com.tomato.daoimplement.OrderDAOImplent;
import com.tomato.model.Order;
import com.tomato.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderServlet")
public class OrdersServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(); 
		User user = (User)session.getAttribute("user");
		OrderDAOImplent orderDao = new OrderDAOImplent();
		List<Order> ordersList= orderDao.getAllOrders(user.getUserId());
		req.setAttribute("ordersList", ordersList);
		RequestDispatcher rd =  req.getRequestDispatcher("orders.jsp");
		rd.forward(req, resp);
	}
}
