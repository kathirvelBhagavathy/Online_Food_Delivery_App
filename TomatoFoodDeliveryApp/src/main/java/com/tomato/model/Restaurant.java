package com.tomato.model;

public class Restaurant {

	private int restaurantId;
	private String name;
	private String address;
	private String phone;
	private float rating;
	private String cuisineType;
	private boolean isActive;
	private String eTA;
	private int adminUserId;
	private String imagePath;
	
	public Restaurant() {
		super();
	}

	public Restaurant(int restaurantId, String name, String address, String phone, float rating, String cuisineType,
			boolean isActive, String eTA, int adminUserId, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cuisineType = cuisineType;
		this.isActive = isActive;
		this.eTA = eTA;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public boolean getisActive() {
		return isActive;
	}

	public void setisActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getETA() {
		return eTA;
	}

	public void setETA(String eTA) {
		this.eTA = eTA;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public String toString() {
		return restaurantId + ", " + name + ", " + address + ", " + phone + ", " + rating + ", " + cuisineType ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
