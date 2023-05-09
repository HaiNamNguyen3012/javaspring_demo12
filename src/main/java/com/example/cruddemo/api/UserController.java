package com.example.cruddemo.api;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.model.User;
import com.example.cruddemo.service.UserService;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
	//Declare a service
	private final UserService userService;

	//Constructor
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	//HTTP CRUD methods for API layer
	//Add user
	@PostMapping
	public void addUser(@Valid @NonNull @RequestBody User user) {
		userService.addUser(user);
	}
	
	//Get All User
	@GetMapping
	public Collection<User> getAllPeople(){
		return userService.getAllUsers();
	}
	
	//Get User by Id
	@GetMapping(path = "{id}")
	public User getUserById(@PathVariable("id") String id) {
		
		return userService.getUserById(id);
	}
	
	//Delete User by Id
	@DeleteMapping(path = "{id}")
	public void deleteUserById(@PathVariable("id") String id) {
		userService.deleteUser(id);
	}
	
	//Update User by id
	@PutMapping(path = "{id}")
	public void updateUser(@PathVariable("id") String id,@RequestBody User userToUpdate) {
		userToUpdate.setId(id);
		userService.updateUser(userToUpdate);
	}
	
}
