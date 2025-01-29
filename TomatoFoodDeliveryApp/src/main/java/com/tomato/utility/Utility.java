package com.tomato.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.tomato.model.Menu;
import com.tomato.model.Order;
import com.tomato.model.OrderItem;
import com.tomato.model.Restaurant;
import com.tomato.model.User;

public class Utility {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "groot";
	private static final String URL = "jdbc:mysql://localhost:3306/tomato";
	static Connection connection;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return connection;
	}
	
	

	public static User setUserValues(ResultSet res) throws SQLException {
		
	int userid = res.getInt("userId");
	String name = res.getString("name");
	String username = res.getString("username");
	String password = res.getString("password");
	String email = res.getString("email");
	String phone = res.getString("phone");
	String address = res.getString("address");
	String role = res.getString("role");
	java.sql.Date createdDate = res.getDate("createdDate");
	java.sql.Date lastLogindate = res.getDate("lastLoginDate");
	
	User user = new User(userid, name,username, password, email, phone, address, role, createdDate, lastLogindate);
	
	return user;
	}



	public static Restaurant setRestaurantValues(ResultSet resultSet) throws SQLException {
		
		int restaurantId = resultSet.getInt(1);
		String name = resultSet.getString(2);
		String address = resultSet.getString(3);
		String phone = resultSet.getString(4);
		float rating = resultSet.getFloat(5);
		String cuisineType = resultSet.getString(6);
		boolean isActive = resultSet.getBoolean(7);
		String eTA = resultSet.getString(8);
		int adminUserId = resultSet.getInt(9);
		String imagePath = resultSet.getString(10);
		
		Restaurant r = new Restaurant(restaurantId, name, address, phone, rating, cuisineType, isActive, eTA, adminUserId, imagePath);
		
		return r;
	}



	public static Menu setMenuValues(ResultSet resultSet) throws SQLException {
		int menuId = resultSet.getInt("menuId");
		int restaurantId = resultSet.getInt("restaurantId");
		String itemName = resultSet.getString("itemName");
		String description = resultSet.getString("description");
		double price = resultSet.getDouble("price");
		float rating = resultSet.getFloat("ratings");
		boolean isAvailable = resultSet.getBoolean("isAvailable");
		String imagePath = resultSet.getString("imagePath");
		
		Menu menu = new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath);
		
		return menu;
	}



	public static Order setOrderValues(ResultSet resultSet) throws SQLException {
		int orderId = resultSet.getInt("orderId");
		int userid = resultSet.getInt("userId");
		int restaurantId = resultSet.getInt("restaurantId");
		Date orderDate = resultSet.getDate("orderDate");
		double totalAmount = resultSet.getDouble("totalAmount");
		String status = resultSet.getString("status");
		String paymentMode = resultSet.getString("paymentMode");
		
		Order order = new Order(orderId, userid, restaurantId, orderDate, totalAmount, status, paymentMode);
		
		return order;
	}



	public static OrderItem setOrderItemValues(ResultSet resultSet) throws SQLException {
		int orderItemId = resultSet.getInt(1);
		int orderId = resultSet.getInt(2);
		int menuId = resultSet.getInt(3);
		int quantity = resultSet.getInt(4);
		double totalPrice = resultSet.getDouble(5);
		
		OrderItem orderItem = new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
		
		return orderItem;
		
	}
	
	

}
