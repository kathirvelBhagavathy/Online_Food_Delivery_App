package com.tomato.model;

public class CartItem {
	private int id;
	private String itemName;
	private double price;
	private int quantity;
	private int restaurantId;
	private String imagePath;
	
	public CartItem(int id, String itemName,int restaurantId, double price, int quantity, String imagePath) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
