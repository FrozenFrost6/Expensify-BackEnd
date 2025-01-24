package com.anvesh.expensify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anvesh.expensify.model.User;
import com.anvesh.expensify.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{userId}")
				.build(createdUser.getUserId());
		
		return ResponseEntity.created(location).build();
    }


    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }


    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
    
    @PostMapping("/admin/make-admin/{username}")
    public User setUserAsAdmin(@PathVariable String username) {
    	return userService.makeUserAsAdmin(username);
    }
}
