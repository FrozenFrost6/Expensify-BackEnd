package com.anvesh.expensify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "expensify_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer userId;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	private String firstName;
	private String lastName;
	
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private ExpensifyUserRole userRole;
	
	public enum ExpensifyUserRole {
		FREE_USER,
		ADMIN
	}
	
	public User(String username, String password, String firstName, String lastName, ExpensifyUserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
	}

	public User() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	} 
	
	public ExpensifyUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(ExpensifyUserRole userRole) {
		this.userRole = userRole;
	}
	
	
}
