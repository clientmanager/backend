package com.clientmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.JobDAO;
import com.clientmanager.dao.UserDAO;
import com.clientmanager.model.Job;
import com.clientmanager.model.Role;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	JobDAO jobDAO;
	
	@Transactional
	@Override
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	@Transactional
	@Override
	public Optional<User> getUserById(int id) {
		return userDAO.findById(id);
	}

	@Transactional
	@Override
	public User createUser(User user) {
		return userDAO.save(user);
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		if(userDAO.existsById(user.getId())) {
			return userDAO.save(user);
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public void deleteUser(int id) {		
		userDAO.deleteById(id);
	}
	
	@Transactional
	@Override
	public List<Job> getAllJobs(int userId) {
		return jobDAO.findAll();
	}

	@Transactional
	@Override
	public Job addJob(int userId, Team team, Role role) {
		Job job = jobDAO.save(new Job(team, role));
		User user = userDAO.findById(userId).orElse(null);
		List<Job> jobs = user.getJobs();
		jobs.add(job);
		user.setJobs(jobs);
		userDAO.save(user);
		return job;
	}

	@Transactional
	@Override
	public void removeJob(int userId, int id) {
		User user = userDAO.findById(userId).orElse(null);
		List<Job> jobs = user.getJobs();
		for(int i = 0; i < jobs.size(); i++) {
			if(jobs.get(i).getId() == id) {
				jobs.remove(i);
			}
		}		
		userDAO.deleteById(id);
		
	}


}
