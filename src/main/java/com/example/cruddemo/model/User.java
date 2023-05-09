package com.example.cruddemo.model;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	//Declare information fields
	private String id;
	private String username;
	private String password;
	private String email;
	
	//Constructor
	public User() {}
	
	public User(@JsonProperty("username") String username,@JsonProperty("password") String password,@JsonProperty("email") String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.id = UUID.randomUUID().toString();
	}

	//Getter and setter id 
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	//Getter and setter username
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	//Getter and setter password
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	//Getter and setter email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
