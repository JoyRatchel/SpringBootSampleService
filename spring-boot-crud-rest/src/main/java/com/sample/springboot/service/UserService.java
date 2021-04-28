package com.sample.springboot.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.springboot.entity.User;
import com.sample.springboot.exception.ResourceNotFoundException;
import com.sample.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public User getUserById(Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		return user;
	}

	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		user.setEmailId(userDetails.getEmailId());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setUpdatedAt(new Date());
		final User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Transactional
	public Map<String, Boolean> deleteUser(Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
