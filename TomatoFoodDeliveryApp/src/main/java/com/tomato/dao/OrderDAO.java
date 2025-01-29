package com.tomato.dao;

import java.util.ArrayList;

import com.tomato.model.Order;

public interface OrderDAO {
	int addorder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	ArrayList<Order> getAllOrders();
	ArrayList<Order> getAllOrders(int userId);
}
