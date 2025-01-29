package com.tomato.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	
	private Map<Integer,CartItem> cartItems = new LinkedHashMap<>();
	private int restaurantId ;
	
	
	public ArrayList<CartItem> getCartItems() {
		ArrayList<CartItem> cartItemslist = new ArrayList<> (cartItems.values());
		return cartItemslist;
	}

	public void setCartItems(Map<Integer, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void addCartItem(CartItem c) {
		if(cartItems.containsKey(c.getId())) {
			CartItem oldCartItem = cartItems.get(c.getId());
			oldCartItem.setQuantity(c.getQuantity() + oldCartItem.getQuantity());
		}
		else {
		cartItems.put(c.getId(),c);
		}
	}
	
	public void updateCartItem(int itemId, int quantity) {
		if(cartItems.containsKey(itemId)) {
			if(quantity<=0) {
				cartItems.remove(itemId);
			}
		else {
		cartItems.get(itemId).setQuantity(quantity);
			}
		}
	}
	
	public void deleteCartItem(int itemId) {
		cartItems.remove(itemId);
	}
	
	public void clearCart() {
		cartItems.clear();
	}
	
	public double totalAmount() {
		double totalAmount=0;
		for(CartItem c : getCartItems()) {
			totalAmount+=(c.getPrice()*c.getQuantity());
		}
		return totalAmount;
	}
}
