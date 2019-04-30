package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Job;
import com.clientmanager.model.Role;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;

public interface UserService {
	public List<User> getAllUsers();
	public User getUserById(int id) throws BusinessExcpetion;
	public User createUser(User user) throws BusinessExcpetion;
	public User updateUser(User user) throws BusinessExcpetion;
	public void deleteUser(User user) throws BusinessExcpetion;
	
	public List<Job> getAllJobs(User user) throws BusinessExcpetion;
	public User addJob(User user, Team team, Role role) throws BusinessExcpetion;
	public User removeJob(User user, Team team, Role role) throws BusinessExcpetion;
	public User updateJob(User user, Team team, Role role) throws BusinessExcpetion;
	List<User> filterUsersByName(String lname);
	List<User> filterUsersByTeam(String team);
	
}
