package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.Job;
import com.clientmanager.model.Role;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;

public interface UserService {
	public List<User> getAllUsers();
	public User getUserById(int id);
	public User createUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
	
	public List<Job> getAllJobs(User user);
	public User addJob(User user, Team team, Role role);
	public User removeJob(User user, Team team, Role role);
	public User updateJob(User user, Team team, Role role);
	List<User> filterUsersByName(String lname);
	
}
