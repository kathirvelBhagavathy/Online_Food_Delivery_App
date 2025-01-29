package com.tomato.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tomato.dao.MenuDAO;
import com.tomato.model.Menu;
import com.tomato.utility.Utility;

public class MenuDAOImplement implements MenuDAO {

	@Override
	public void addMenu(Menu menu) {
		String INSERT_MENU_QUERY = "insert into `menu` (`menuId`, `restaurantId`, `itemName`, `description`, `price`,"
									+ " `isAvailable`, `imagePath`) values (?,?,?,?,?,?,?)";
		
		try (Connection con = Utility.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(INSERT_MENU_QUERY);)
		{
			prepareStatement.setInt(1, menu.getMenuId());
			prepareStatement.setInt(2, menu.getRestaurantId());
			prepareStatement.setString(3, menu.getItemName());
			prepareStatement.setString(4, menu.getDescription());
			prepareStatement.setDouble(5, menu.getPrice());
			prepareStatement.setBoolean(6, menu.getIsAvailable());
			prepareStatement.setString(7, menu.getImagePath());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0? result+" menu(s) added" : "Menu not added");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuId) {
		String GET_MENU_QUERY = "Select * from `menu` where `menuId` = ?";
		Menu menu = null;
		
		try(Connection connection = Utility.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_QUERY);)
		{
			
			prepareStatement.setInt(1, menuId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
			menu = Utility.setMenuValues(resultSet);
			}
			else {
				System.out.println("No data found on the given id");
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		String UPDATE_MENU_QUERY = "update `menu` set `itemName` = ?, `description` = ?, `price` = ?, `isAvailable` = ?, `imagePath` = ?"
									+ "where `menuId` = ? ";
		
		try(Connection connection = Utility.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_MENU_QUERY);) {
			prepareStatement.setString(1, menu.getItemName());
			prepareStatement.setString(2, menu.getDescription());
			prepareStatement.setDouble(3, menu.getPrice());
			prepareStatement.setBoolean(4, menu.getIsAvailable());
			prepareStatement.setString(5, menu.getImagePath());
			prepareStatement.setInt(6, menu.getMenuId());
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0 ? result + " menu updated" : "menu not updated");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMenu(int menuId) {
		String DELETE_MENU_QUERY = "delete from menu where `menuId` = ?";
		
		
		try(Connection connection = Utility.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_MENU_QUERY);) 
		{
			
			prepareStatement.setInt(1, menuId);
			int result = prepareStatement.executeUpdate();
			System.out.println(result>0? result + " item(s) deleted" : result + " item deleted");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		

	}

	@Override
	public List<Menu> getAllMenu() {
		String GET_ALL_MENU_ITEMS_QUERY = "select * from menu";
		Connection connection = Utility.getConnection();
		Statement statement =null;
		ArrayList<Menu> menuLists = new ArrayList<Menu>();
		
		try {
			statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(GET_ALL_MENU_ITEMS_QUERY);
			while(resultset.next()) {
				Menu menu = Utility.setMenuValues(resultset);
				menuLists.add(menu);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return menuLists;
	}
	
	@Override
	public List<Menu> getMenuByRestaurant(int restaurantId) {
		String GET_ALL_MENU_BY_RESTAURANT_QUERY = "select * from menu where `restaurantId` = ?";
		Connection connection = Utility.getConnection();
		PreparedStatement statement =null;
		ArrayList<Menu> menuLists = new ArrayList<Menu>();
		try {
			statement = connection.prepareStatement(GET_ALL_MENU_BY_RESTAURANT_QUERY);
			statement.setInt(1,restaurantId);
			ResultSet resultset = statement.executeQuery();
			while(resultset.next()) {
				Menu menu = Utility.setMenuValues(resultset);
				menuLists.add(menu);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return menuLists;
	}

}
