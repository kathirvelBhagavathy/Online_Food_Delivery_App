package com.tomato.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tomato.dao.OrderItemDAO;
import com.tomato.model.OrderItem;
import com.tomato.utility.Utility;

public class OrderItemDAOImplement implements OrderItemDAO {

	@Override
	public void addOrderItem(OrderItem orderItem) {
		String ADD_ORDERITEM_QUERY = "insert into `orderItem` (`orderId`, `menuId`, `quantity`,"
									+ " `totalPrice`) values (?,?,?,?)";
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(ADD_ORDERITEM_QUERY);
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalPrice());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0 ? result + " order item(s) added" : "No order items added");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		String GET_ORDERITEM_QUERY = "select * from `orderItem` where `orderItemid` = ?";
		Connection connection = Utility.getConnection();
		OrderItem orderItem = null;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDERITEM_QUERY);
			prepareStatement.setInt(1, orderItemId);
			ResultSet resultSet = prepareStatement.executeQuery();
			orderItem = Utility.setOrderItemValues(resultSet);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		String UPDATE_ORDERITEM_QUERY = "update `orderItem` set `orderId` = ?, `menuId` = ?, `quantity` = ?, `totalPrice` = ? where `orderItemId` = ? ";
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_ORDERITEM_QUERY);
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalPrice());
			prepareStatement.setInt(5, orderItem.getOrderItemId());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0 ? result + " orderItem updated" : "No orderItems updated");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		String DELETE_ORDERITEM_QUERY = "delete from `orderItem` where orderItemId = ?";
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDERITEM_QUERY);
			prepareStatement.setInt(1, orderItemId);
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0 ? result + " orderItem deleted" : "No orderItems deleted");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		String GET_ALL_ORDERITEMS_QUERY = "select * from `orderItem`";
		ArrayList<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		Connection connection = Utility.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERITEMS_QUERY);
			while(resultSet.next()) {
				OrderItem orderItem = Utility.setOrderItemValues(resultSet);
				orderItemsList.add(orderItem);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return orderItemsList;
	}
	
	@Override
	public List<OrderItem> getAllOrderItemsByOrder(int orderId){
		String GET_ALL_ORDERITEMS_QUERY_BY_ORDER = "select * from `orderItem` where orderId = ?";
		ArrayList<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_ORDERITEMS_QUERY_BY_ORDER);
			prepareStatement.setInt(1, orderId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				OrderItem orderItem = Utility.setOrderItemValues(resultSet);
				orderItemsList.add(orderItem);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return orderItemsList;
	}

}
