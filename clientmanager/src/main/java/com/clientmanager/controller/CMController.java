package com.clientmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.model.Permission;
import com.clientmanager.model.User;
import com.clientmanager.service.PermissionService;
import com.clientmanager.service.UserService;

@RestController
@RequestMapping("/api")
public class CMController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	
	@GetMapping("/permission")
	public List<Permission> getAllPermissions() {
		return permissionService.getAllPermissions();
	}


}
