package com.tomato.daoimplement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tomato.dao.UserDAO;
import com.tomato.model.User;
import com.tomato.utility.Utility;

public class UserDAOImplement implements UserDAO {
	String INSERT_QUERY = "Insert into user (`name`, `username`, `password`, `email`, `phone`, `address`, `role`) "
			+ "values (?,?,?,?,?,?,?)";
	String GET_USER_QUERY = "select * from `user` where `userId`= ?";
	@Override
	public void addUser(User user) {
		
		try(Connection connection = Utility.getConnection();) {
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setString(1,user.getName() );
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhone());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			int res = prepareStatement.executeUpdate();
			System.out.println(res>0 ? res +" User(s) added":"User not added");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		

	}

	@Override
	public User getUser(int userId) {
		User user =null;
		try(Connection connection = Utility.getConnection();) {
			PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_QUERY);
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
			user = Utility.setUserValues(resultSet);
			}
			else {
				System.out.println("Result Set not created");
			}
		} 
		catch (SQLException e) 
		{
			 e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User getUser(String username) {
		User user =null;
		String GET_USER_BY_USERNAME_QUERY = "select * from `user` where `username` = ?";
											
		try(Connection connection = Utility.getConnection();) {
			PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_USERNAME_QUERY);
			prepareStatement.setString(1, username);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
			user = Utility.setUserValues(resultSet);
			}
			else {
				System.out.println("Result Set not created");
			}
		} 
		catch (SQLException e) 
		{
			 e.printStackTrace();
		}
		return user;
		
	}

	@Override
	public int updateUser(User user) {
		int result=0;
		String UPDATE_QUERY = "Update user set `name` = ?, `email` = ?, `phone` = ?, `address` = ?  where `userId` = ?";
		try(Connection connection = Utility.getConnection();) {
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setInt(5, user.getUserId());
			result = prepareStatement.executeUpdate();
			System.out.println(result>0?result + " user(s) updated" : "Update failed");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int updatePassword(User user) {
		int result=0;
		String UPDATE_PASSWORD_QUERY = "Update user set `password` = ? where `userId` = ?";
		try(Connection connection = Utility.getConnection();) {
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_PASSWORD_QUERY);
			prepareStatement.setString(1, user.getPassword());
			prepareStatement.setInt(2, user.getUserId());
			result = prepareStatement.executeUpdate();
			System.out.println(result>0?result + " user(s) updated" : "Update failed");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void deleteUser(int userId) {
		String DELETE_QUERY = "delete from `user` where `userId`= ? ";
		try(Connection connection = Utility.getConnection();) {
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, userId);
			int res = prepareStatement.executeUpdate();
			System.out.println(res>0?res + " User(s) deleted" : "Deleting failed");

		} 
		catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		

	}

	@Override
	public List<User> getAllUser() {
		
		String GET_ALL_USER_QUERY = "select * from user";
		Statement statement = null;
		ResultSet resultSet = null;
		
		ArrayList<User> usersList = new ArrayList<User>(); 
		try(Connection connection = Utility.getConnection();) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_ALL_USER_QUERY);
			while(resultSet.next()) {
				User user = Utility.setUserValues(resultSet);
				usersList.add(user);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usersList;
	}

	
}


