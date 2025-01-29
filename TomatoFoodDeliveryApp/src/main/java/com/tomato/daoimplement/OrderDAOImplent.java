package com.tomato.daoimplement;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tomato.dao.OrderDAO;
import com.tomato.model.Order;
import com.tomato.utility.Utility;

public class OrderDAOImplent implements OrderDAO {

	@Override
	public int addorder(Order order) {
		String INSERT_ORDER_QUERY = "insert into `orders` (`userId`, `restaurantId`, `totalAmount`,"
									+ " `status`, `paymentMode`) values (?,?,?,?,?)";
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_ORDER_QUERY,Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1,order.getUserId());
			prepareStatement.setInt(2,order.getRestaurantId());
			prepareStatement.setDouble(3,order.getTotalAmount());
			prepareStatement.setString(4,order.getStatus());
			prepareStatement.setString(5,order.getPaymentMode());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0 ? result + " orders added" : "No orders added");
			if(result>0) {
				ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    return orderId;
                }
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return -1;
	}
	

	@Override
	public Order getOrder(int orderId) {
		String GET_ORDER_QUERY = "select * from `orders` where `orderId` = ?";
		Order order = null;
		
		try (Connection connection = Utility.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDER_QUERY);){
			prepareStatement.setInt(1, orderId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
			order = Utility.setOrderValues(resultSet);
			}
			else {
				System.out.println("No data found on the given Id");
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return order;
	}

	
	@Override
	public void updateOrder(Order order) {
		String UPDATE_ORDER_QUERY = "update `orders` set `restaurantId` = ?, `totalAmount` = ?, `status` = ?, `paymentMode` = ? where `orderId` = ?";
		Connection connection = Utility.getConnection();
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);
			prepareStatement.setInt(1, order.getRestaurantId());
			prepareStatement.setDouble(2, order.getTotalAmount());
			prepareStatement.setString(3, order.getStatus());
			prepareStatement.setString(4, order.getPaymentMode());
			prepareStatement.setInt(5, order.getOrderId());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0?result + " order(s) updated" : "No orders updated");		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public void deleteOrder(int orderId) {
		String DELETE_ORDER = "delete from `orders` where `orderId` = ?";
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_ORDER);
			prepareStatement.setInt(1, orderId);
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0?result + " order(s) deleted" : "No orders deleted");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public ArrayList<Order> getAllOrders() {
		String GET_ALL_ORDERS = "select * from `orders`";
		ArrayList<Order> ordersList = new ArrayList<Order>();
		Connection connection = Utility.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS);
			while(resultSet.next()) {
				Order order = Utility.setOrderValues(resultSet);
				ordersList.add(order);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return ordersList;
	}
	
	@Override
	public ArrayList<Order> getAllOrders(int userId) {
		String GET_ALL_ORDERS_BY_USER = "select * from `orders` where `userId` = ?";
		ArrayList<Order> ordersList = new ArrayList<Order>();
		Connection connection = Utility.getConnection();
		try {
			PreparedStatement preparestatement = connection.prepareStatement(GET_ALL_ORDERS_BY_USER);
			preparestatement.setInt(1, userId);
			ResultSet resultSet = preparestatement.executeQuery();
			while(resultSet.next()) {
				Order order = Utility.setOrderValues(resultSet);
				ordersList.add(order);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return ordersList;
	}

}
