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
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.model.User;
import com.clientmanager.service.UserService;


@RestController
public class CMController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		System.out.println("getit");
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable int id){
		return userService.getUserById(id);
	}
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user){
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
	
}
