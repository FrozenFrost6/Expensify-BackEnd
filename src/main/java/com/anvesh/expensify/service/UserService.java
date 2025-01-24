package com.anvesh.expensify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anvesh.expensify.exceptions.DuplicateUsernameException;
import com.anvesh.expensify.exceptions.UserNotFoundException;
import com.anvesh.expensify.model.User;
import com.anvesh.expensify.model.User.ExpensifyUserRole;
import com.anvesh.expensify.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//	// Get user by ID
//	public User getUserById(Integer id) {
//
//		Optional<User> userById = userRepository.findById(id);
//
//		if (userById.isEmpty()) {
//			throw new UserNotFoundException("There is no user found with id: " + id);
//		}
//
//		return userById.get();
//	}
//	
	
	public User getUserByUsername(String username) {
		Optional<User> userByUsername = userRepository.findByUsername(username);
		if (userByUsername.isEmpty()) {
			throw new UserNotFoundException("There is no user found with username: " + username);
		}

		return userByUsername.get();
	}

	public User createUser(User user) {
		try {
			user.setUserRole(ExpensifyUserRole.FREE_USER);
	    	user.setUserId(null);
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateUsernameException("Username already exists!");
		}
		
	}



	public User updateUser(String username, User updatedUser) {
		User existingUser = getUserByUsername(username);

		if (updatedUser.getFirstName() != null) {
			existingUser.setFirstName(updatedUser.getFirstName());
		}

		if (updatedUser.getLastName() != null) {
			existingUser.setLastName(updatedUser.getLastName());
		}
		
		

		if (updatedUser.getPassword() != null) {
			existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));;
		}

		return userRepository.save(existingUser);

	}

	@Transactional
	public void deleteUser(String username) {
		userRepository.deleteByUsername(username);
	}
	
	public User makeUserAsAdmin(String username) {
		User existingUser = getUserByUsername(username);
		existingUser.setUserRole(ExpensifyUserRole.ADMIN);
		return userRepository.save(existingUser);
	}
}
