package com.tomato.dao;

import java.util.List;

import com.tomato.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userId);
	User getUser(String username);
	int updateUser(User user);
	int updatePassword(User user);
	void deleteUser(int userId);
	List<User> getAllUser();
}
