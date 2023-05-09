package com.example.cruddemo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.cruddemo.dao.UserDao;
import com.example.cruddemo.model.User;

@Service
public class UserService {
	//Declare an user DAO
	private final UserDao userDao;
	
	//Constructor
	@Autowired
	public UserService(@Qualifier("postgres") UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	//CRUD methods for service layer
	//Add user
	public User addUser(User user) {
		return userDao.insertUser(user);
	}
	
	//Get All User
	public Collection<User> getAllUsers(){
		return userDao.selectAllUsers();
	}
	
	//Get User By Id
	public User getUserById(String id){
		return userDao.selectUserById(id);
	}
	
	//Delete User
	public User deleteUser(String id) {
		return userDao.deleteUserById(id);
	}
	
	//Update User
	public User updateUser(User newUser) {
		return userDao.updateUserById(newUser);
	}
}
