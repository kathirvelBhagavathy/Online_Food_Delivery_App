package com.tomato.dao;

import java.util.List;

import com.tomato.model.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getAllOrderItems();
	List<OrderItem> getAllOrderItemsByOrder(int orderId);
}
