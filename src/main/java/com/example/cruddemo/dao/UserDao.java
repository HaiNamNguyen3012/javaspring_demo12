package com.example.cruddemo.dao;

import java.util.Collection;

import com.example.cruddemo.model.User;

public interface UserDao {
	//Declare data interaction methods
	
	//Insert user
	User insertUser(User user) ;
	
	//Get all user
	Collection<User> selectAllUsers();
	
	//Get user by id
	User selectUserById(String id);
	
	//Update user
	User updateUserById(User user);
	
	//Delete user
	User deleteUserById(String id);
}
