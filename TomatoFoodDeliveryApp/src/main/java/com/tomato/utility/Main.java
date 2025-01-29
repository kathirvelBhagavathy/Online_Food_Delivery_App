package com.tomato.utility;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
/*import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Formatter;*/
import java.util.Scanner;

import com.tomato.daoimplement.MenuDAOImplement;
import com.tomato.daoimplement.OrderDAOImplent;
import com.tomato.daoimplement.OrderItemDAOImplement;
import com.tomato.daoimplement.RestaurantDAOImplement;
import com.tomato.daoimplement.UserDAOImplement;
import com.tomato.model.*;

	public class Main 
	{
		public static void main(String[] args) {
			UserCalls uc = new UserCalls();
//			uc.addingUser();
//			uc.getUser();
//			uc.updateUser();
//			uc.deleteUser();
//			uc.getAllUsers();
//			uc.getUserByName();

			
//	RestaurantCalls resCalls = new RestaurantCalls();
//		resCalls.addRestaurant();
//		RestaurantCalls.getRestaurant();
//		RestaurantCalls.updateRestaurant();
//		RestaurantCalls.deleteRestaurant();
//		RestaurantCalls.getAllRestaurants();
		
//		MenuCalls.addMenu();
//		MenuCalls.getMenu();
//		MenuCalls.updateMenu();
//		MenuCalls.deleteMenu();
//		MenuCalls.getAllMenu();
		
//		OrderCalls.addOrder();
//		OrderCalls.getOrder();
//		OrderCalls.updateOrder();
//		OrderCalls.deleteOrder();
//		OrderCalls.getAllOrder();
//			OrderCalls.getAllOrderByUser();
		
		OrderItemCalls.getOrderItem();
		
		}
	}

	class UserCalls {
		static UserDAOImplement udi = new UserDAOImplement();
		
		static void addingUser() {
		
		/*System.out.println("Enter name");
		String name = scan.nextLine();
		System.out.println("Enter Username");
		String username = scan.nextLine();
		System.out.println("Enter Password");
		String password = scan.nextLine();
		System.out.println("Enter email");
		String email = scan.nextLine();
		System.out.println("Enter Phone No");
		String phone = scan.nextLine();
		System.out.println("Enter address");
		String address = scan.nextLine();
		System.out.println("Enter role - User or AdminUser or RestuarantUser or DeliveryAgent");
		String role = scan.nextLine();
		Date createdDate = new Date(new java.util.Date().getTime());
		User user = new User(name,username,password,email,phone,address,role,createdDate);
		
		udi.addUser(user);*/
		}
		
		static void getUser() {
		
		/*System.out.println("Enter the userId");
		int userId = scan.nextInt();
		System.out.println(udi.getUser(userId));*/
		}
		
		static void getUserByName() {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the username");
			String username = scan.next();
			User user = udi.getUser(username);
			System.out.println(user.getPassword());
			
			
		}
		
		static void updateUser() {
		
		/*System.out.println("Enter the userId");
		int userId = scan.nextInt();
		scan.nextLine();
		User user = udi.getUser(userId);
		System.out.println("Enter name");
		user.setName(scan.nextLine());
		System.out.println("Enter Password");
		user.setPassword(scan.nextLine());
		System.out.println("Enter email");
		user.setEmail(scan.nextLine());
		System.out.println("Enter Phone No");
		user.setPhone(scan.nextLine());
		System.out.println("Enter address");
		user.setAddress(scan.nextLine());
		System.out.println("Enter role - User or AdminUser or RestuarantUser or DeliveryAgent");
		user.setRole(scan.nextLine());
		
		udi.updateUser(user);*/
		}
		
		static void deleteUser() {
		/*System.out.println("Enter the userId to delete");
		int userId = scan.nextInt();
		udi.deleteUser(userId);*/
		}
		
		static void getAllUsers() {
		
		List<User> usersList = udi.getAllUser();		
		for(User user : usersList) {			// for(User user : udi.getAllUser();
			System.out.println(user);
			}
		}
	}
	
	class RestaurantCalls {
		static Scanner scan = new Scanner(System.in);
		 static RestaurantDAOImplement rdi = new RestaurantDAOImplement();
		
		static void addRestaurant() {
			System.out.println("Enter the restaurant ID");
			int restaurantId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter the restaurant name");
			String name = scan.nextLine();
			System.out.println("Enter the restaurant address");
			String address = scan.nextLine();
			System.out.println("Enter the restaurant phone no");
			String phone = scan.nextLine();
			System.out.println("Enter the restaurant cuisine type");
			String cuisineType = scan.nextLine();
			System.out.println("Enter the restaurant is either active or not by (true or false)");
			boolean isActive = scan.nextBoolean();
			System.out.println("Enter the restaurant User Id");
			int restaurantUserId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter the restaurant image directory or path");
			String imagePath = scan.nextLine();
			
			Restaurant restaurant = new Restaurant(restaurantId, name, address, phone, 0.0f , cuisineType, isActive, null, restaurantUserId, imagePath);
			rdi.addRestaurant(restaurant);
			
		}

		public static void getRestaurant() {
			System.out.println("Enter the retaurant id");
			int restaurantId = scan.nextInt();
			Restaurant restaurant = rdi.getRestaurant(restaurantId);
			System.out.println(restaurant);
		}
		
		public static void updateRestaurant() {
			System.out.println("Enter the restaurantId to update");
			int restaurantId = scan.nextInt();
			scan.nextLine();
			Restaurant restaurant = rdi.getRestaurant(restaurantId);
			System.out.println("Enter the restaurant name");
			restaurant.setName(scan.nextLine());
			System.out.println("Enter the restaurant address");
			restaurant.setAddress(scan.nextLine());
			System.out.println("Enter the restaurant phone no");
			restaurant.setPhone(scan.nextLine());
			System.out.println("Enter the restaurant cuisine type");
			restaurant.setCuisineType(scan.nextLine());
			System.out.println("Enter the restaurant is either active or not by (true or false)");
			restaurant.setisActive(scan.nextBoolean());
			scan.nextLine();
			System.out.println("Enter the restaurant image directory or path");
			restaurant.setImagePath(scan.nextLine());
			rdi.updateRestaurant(restaurant);
		}

		public static void deleteRestaurant() {
			System.out.println("Enter the restaurantId to delete");
			int restaurantId = scan.nextInt();
			scan.nextLine();
			rdi.deleteRestaurant(restaurantId);
			
		}
		
		public static void getAllRestaurants() {
			for(Restaurant restaurant : rdi.getAllRestaurants()) {
				System.out.println(restaurant);
			}
			
		}
	}
	
	class MenuCalls {
		static Scanner scan = new Scanner(System.in);
		static MenuDAOImplement mdi = new MenuDAOImplement();

		public static void addMenu() {
			System.out.println("Enter the menu Id");
			int menuId = scan.nextInt();
			System.out.println("Enter the restaurant ID");
			int restaurantId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter the Item name");
			String itemName = scan.nextLine();
			System.out.println("Enter the description about the item");
			String description = scan.nextLine();
			System.out.println("Enter the Price of the item");
			double price = scan.nextDouble();
			System.out.println("Enter whether the item is available or not (true or false)");
			boolean isAvailable = scan.nextBoolean();
			scan.nextLine();
			System.out.println("Enter the image directory for the item");
			String imagePath = scan.nextLine();
			
			Menu menu = new Menu(menuId, restaurantId, itemName, description, price, 0.0f, isAvailable, imagePath);
			mdi.addMenu(menu);
			
		}

		public static void getAllMenu() {
			for(Menu menu : mdi.getAllMenu()) {
				System.out.println(menu);
			}
			
		}

		public static void deleteMenu() {
			System.out.println("Enter the menu Id for deleting");
			int menuId = scan.nextInt();
			mdi.deleteMenu(menuId);
			
		}

		public static void updateMenu() {
			System.out.println("Enter the menu Id for updating");
			int menuId = scan.nextInt();
			scan.nextLine();
			Menu menu = mdi.getMenu(menuId);
			System.out.println("Enter the menu Name for Updating");
			menu.setItemName(scan.nextLine());
			System.out.println("Enter the description for Updating");
			menu.setDescription(scan.nextLine());
			System.out.println("Enter the menu price for Updating");
			menu.setPrice(scan.nextDouble());
			System.out.println("Enter the menu is available or not by (true or false)");
			menu.setIsAvailable(scan.nextBoolean());
			scan.nextLine();
			System.out.println("Enter the menu image directory or path for Updating");
			menu.setImagePath(scan.nextLine());
			mdi.updateMenu(menu);
		}

		public static void getMenu() {
			System.out.println("Enter the menu Id");
			int menuId = scan.nextInt();
			System.out.println(mdi.getMenu(menuId));
			
		}
		
	}
	
	class OrderCalls{
		static Scanner scan = new Scanner(System.in);
		static OrderDAOImplent odi = new OrderDAOImplent();

		public static void addOrder() {
			System.out.println("Enter the order id");
			int orderId = scan.nextInt();
			System.out.println("Enter the user id");
			int userId = scan.nextInt();
			System.out.println("Enter the restaurant id");
			int restauranId = scan.nextInt();
			Date orderDate = new Date(new java.util.Date().getTime());
			System.out.println("Enter the total amount of Order");
			double totalAmount = scan.nextDouble();
			scan.nextLine();
			System.out.println("Enter the status of Order");			
			String status = scan.next();
			System.out.println("Enter the payment mode of Order");			
			String paymentMode = scan.next();
			Order order = new Order(orderId, userId, restauranId, orderDate, totalAmount, status, paymentMode);
			odi.addorder(order);
			
		}

		public static void getAllOrder() {
			for(Order order : odi.getAllOrders()) {
				System.out.println(order);
			}
			
		}

		public static void deleteOrder() {
			System.out.println("Enter the order Id to delete");
			odi.deleteOrder(scan.nextInt());
			
		}

		public static void updateOrder() {
			System.out.println("Enter the order id to update");
			int orderId = scan.nextInt();
			Order order = odi.getOrder(orderId);
			System.out.println("Enter the user id");
			order.setUserId(scan.nextInt());
			System.out.println("Enter the restaurant id");
			order.setRestaurantId(scan.nextInt());
			System.out.println("Enter the total amount of Order");
			order.setTotalAmount(scan.nextDouble());
			System.out.println("Enter the status of Order");			
			order.setStatus(scan.next());
			System.out.println("Enter the payment mode of Order");			
			order.setPaymentMode(scan.next());
			
			odi.updateOrder(order);
			
		}

		public static void getOrder() {
			System.out.println("Enter the order Id to get");
			System.out.println(odi.getOrder(scan.nextInt()));
			
		}
		
		public static void getAllOrderByUser() {
			System.out.println("Enter the order Id to get");
			System.out.println(odi.getAllOrders(scan.nextInt()));
			
		}
		
	}
	
	class OrderItemCalls{
		static OrderItemDAOImplement oidi = new OrderItemDAOImplement();
		static Scanner scan = new Scanner(System.in);
		static void getOrderItem()
		{
			System.out.println("Enter the orderid");
			List<OrderItem> orderItems= oidi.getAllOrderItemsByOrder(scan.nextInt());
			for(OrderItem o: orderItems) {
			System.out.println(o.getTotalPrice());
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	

