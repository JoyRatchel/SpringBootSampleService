package com.sample.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.entity.User;
import com.sample.springboot.exception.ResourceNotFoundException;
import com.sample.springboot.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(
			@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userService.getUserById(userId);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userService.saveUser(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(
			@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userService.updateUser(userId,userDetails);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(
			@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Map<String, Boolean> response = userService.deleteUser(userId);
		return response;
	}
}
