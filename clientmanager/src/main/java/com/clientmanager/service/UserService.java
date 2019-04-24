package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.User;

public interface UserService {
	public List<User> getAllUsers();
	public Optional<User> getUserById(int id);
	public User createUser(User user);
	public User updateUser(User user);
	public void deleteUser(int id);
	
}
