package com.tomato.dao;

import java.util.List;

import com.tomato.model.Restaurant;

public interface RestaurantDAO {
		
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restauarantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restauarantId);
	List<Restaurant> getAllRestaurants();
	
}
