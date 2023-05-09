package com.example.cruddemo.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.User;

@Repository("fakeDao")
public class ListBasedDao implements UserDao {
	// Declare user database
	private static Map<String, User> DB = new HashMap<>();

	// CRUD methods for data layer
	// Insert user to database
	@Override
	public User insertUser(User user) {
		// TODO Auto-generated method stub
		DB.put(user.getId(), user);
		return user;
	}

	// Select all users from database
	@Override
	public Collection<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return DB.values();
	}

	// Select user from database by id
	@Override
	public User selectUserById(String id) {
		// TODO Auto-generated method stub
		if (!DB.containsKey(id)) {
			return null;
		}
		User user = DB.get(id);
		return user;
	}

	// Update user in database by id
	@Override
	public User updateUserById(User user) {
		// TODO Auto-generated method stub
		if (!DB.containsKey(user.getId())) {
			return null;
		}
		User newUser = DB.replace(user.getId(), user);
		return newUser;
	}

	// Delete user in database by id
	@Override
	public User deleteUserById(String id) {
		// TODO Auto-generated method stub
		if (!DB.containsKey(id)) {
			return null;
		}
		User user = selectUserById(id);
		DB.remove(id, user);
		return user;
	}
}
