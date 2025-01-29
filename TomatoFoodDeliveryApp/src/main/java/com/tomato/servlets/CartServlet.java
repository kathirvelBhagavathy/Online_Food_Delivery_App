package com.tomato.servlets;

import java.io.IOException;

import com.tomato.daoimplement.MenuDAOImplement;
import com.tomato.model.Cart;
import com.tomato.model.CartItem;
import com.tomato.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		Integer currentRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart", cart);
			cart.setRestaurantId(currentRestaurantId);
		}
		if(cart.getRestaurantId()!=0) {
		if(currentRestaurantId==null || cart.getRestaurantId()!=currentRestaurantId) {
			cart = new Cart();
			session.removeAttribute("cart");
			session.setAttribute("cart", cart);
			cart.setRestaurantId(currentRestaurantId);
		}
		}
		
		int menuId = Integer.parseInt(req.getParameter("itemId"));
		String action = req.getParameter("action");
		MenuDAOImplement menuDao = new MenuDAOImplement();
		Menu menu = menuDao.getMenu(menuId);
		
			if(action.equals("add")) {
				if(menu!=null) {
					int quantity = Integer.parseInt(req.getParameter("quantity"));
					CartItem cartItem = new CartItem(menuId,menu.getItemName(),menu.getRestaurantId(), menu.getPrice(), quantity, menu.getImagePath());
					cart.addCartItem(cartItem);
					req.setAttribute("restaurantId", menu.getRestaurantId());
					RequestDispatcher rd = req.getRequestDispatcher("menuservlet");
					rd.forward(req, resp);
				}
			}
			
			else if(action.equals("update")) {
				int quantity = Integer.parseInt(req.getParameter("quantity"));
				cart.updateCartItem(menuId, quantity);
				resp.sendRedirect("cart.jsp");
			}
			
			else if(action.equals("delete")) {
				cart.deleteCartItem(menuId);
				resp.sendRedirect("cart.jsp");
				if(cart.getCartItems().size()==0) {
					session.removeAttribute("cart");
				}
			}
		
	}

}
