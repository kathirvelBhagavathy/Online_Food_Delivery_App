package com.tomato.servlets;

import java.io.IOException;

import com.tomato.daoimplement.OrderDAOImplent;
import com.tomato.daoimplement.OrderItemDAOImplement;
import com.tomato.model.Cart;
import com.tomato.model.CartItem;
import com.tomato.model.Order;
import com.tomato.model.OrderItem;
import com.tomato.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/addOrderServlet")
public class AddOrderServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		String paymentMode = req.getParameter("payment");
		System.out.println("Payment method is " +  paymentMode);
		Order order = new Order(user.getUserId(), cart.getRestaurantId(), cart.totalAmount(),"inTransit",paymentMode);
		OrderDAOImplent orderDao = new OrderDAOImplent();
		int orderId =  orderDao.addorder(order);
		
		if(orderId>0) {
			for(CartItem c : cart.getCartItems()) {
				int itemId = c.getId();
				int quantity = c.getQuantity();
				double totalPrice = c.getPrice()*quantity;
				OrderItem orderItem = new OrderItem(orderId, itemId, quantity, totalPrice);
				OrderItemDAOImplement orderItemDao = new OrderItemDAOImplement();
				orderItemDao.addOrderItem(orderItem);
			}
		}
		session.removeAttribute("cart");
		session.setAttribute("orderId", orderId);
		resp.sendRedirect("orderConfirmation.jsp");
		
	}
}
