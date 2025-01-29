package com.tomato.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tomato.dao.RestaurantDAO;
import com.tomato.model.Restaurant;
import com.tomato.utility.Utility;

public class RestaurantDAOImplement implements RestaurantDAO {
	

	@Override
	public void addRestaurant(Restaurant restaurant) {
		String INSERT_QUERY = "insert into `restaurant` (`restaurantId`, `name`, `address`, `phone`, `rating`, "
							+ "`cuisineType`, `isActive`, `ETA`, `adminUserId`, `imagePath`) values (?,?,?,?,?,?,?,?,?,?)";
		try(Connection con = Utility.getConnection();) {
			PreparedStatement prepareStatement = null;
			prepareStatement = con.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1,restaurant.getRestaurantId());
			prepareStatement.setString(2,restaurant.getName());
			prepareStatement.setString(3,restaurant.getAddress());
			prepareStatement.setString(4,restaurant.getPhone());
			prepareStatement.setFloat(5,restaurant.getRating());
			prepareStatement.setString(6,restaurant.getCuisineType());
			prepareStatement.setBoolean(7,restaurant.getisActive());
			prepareStatement.setString(8,restaurant.getETA());
			prepareStatement.setInt(9,restaurant.getAdminUserId());
			prepareStatement.setString(10,restaurant.getImagePath());
			
			int res = prepareStatement.executeUpdate();
			System.out.println(res>0 ? res +"restaurant added":"Restaurant not added");
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
	}

	@Override
	public Restaurant getRestaurant(int restauarantId) {
		String GET_RESTAURANT_QUERY = "Select * from `restaurant` where `restaurantId` = ?";
		Restaurant restaurant = null;
		try(Connection con = Utility.getConnection();
			PreparedStatement prepareStatement = con.prepareStatement(GET_RESTAURANT_QUERY); )
		{	
			prepareStatement.setInt(1, restauarantId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
			restaurant = Utility.setRestaurantValues(resultSet);
			}
			else {
				System.out.println("Wrong id you provided " + restauarantId);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		String UPDATE_RESTAURANT_QUERY = "update `restaurant` set `name` = ?, `address` = ?, `phone` = ?,`cuisineType` = ?,"
										+ "`isActive` = ?, `imagePath` = ? where `restaurantId` = ?";
		
		try(Connection con = Utility.getConnection();PreparedStatement prepareStatement = con.prepareStatement(UPDATE_RESTAURANT_QUERY);) {
			
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhone());
			prepareStatement.setString(4, restaurant.getCuisineType());
			prepareStatement.setBoolean(5, restaurant.getisActive());
			prepareStatement.setString(6, restaurant.getImagePath());
			prepareStatement.setInt(7, restaurant.getRestaurantId());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0?result + " row(s) updated" : "Update failed");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteRestaurant(int restauarantId) {
		String DELETE_QUERY = "delete from `restaurant` where `restaurantId` = ?";
		try(Connection con = Utility.getConnection();PreparedStatement prepareStatement = con.prepareStatement(DELETE_QUERY);) 
		{
			prepareStatement.setInt(1, restauarantId);
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0?result + " restaurant(s) deleted" : "Deleting failed wrong id");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		String GET_ALL_RESTAURANT_QUERY = "select * from `restaurant`";
		ArrayList<Restaurant> restaurantsLists = new ArrayList<>();
		try(Connection con = Utility.getConnection();Statement Statement = con.createStatement();) 
		{
			ResultSet resultSet = Statement.executeQuery(GET_ALL_RESTAURANT_QUERY);
			while(resultSet.next()) {
				Restaurant restaurant = Utility.setRestaurantValues(resultSet);
				restaurantsLists.add(restaurant);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return restaurantsLists;
	}
	
	

}
