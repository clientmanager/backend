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
	public List<User> filterUsersByName(String lname) {
		List<User> users = userDAO.findAll();
		for(int i = 0; i < users.size(); i++) {
			if(!users.get(i).getLname().equals(lname)) {
				users.remove(i);
				i--;
			}
		}
		return users;
	}

	@Transactional
	@Override
	public User getUserById(int id) {
		return userDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public User createUser(User user) {
		return userDAO.save(user);
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		if (userDAO.existsById(user.getId())) {
			return userDAO.save(user);
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	@Transactional
	@Override
	public List<Job> getAllJobs(User user) {
		return user.getJobs();
	}

	@Transactional
	@Override
	public User addJob(User user, Team team, Role role) {
		Job job = jobDAO.save(new Job(team, role));
		List<Job> jobs = user.getJobs();
		jobs.add(job);
		user.setJobs(jobs);
		userDAO.save(user);
		return user;
	}

	@Transactional
	@Override
	public User removeJob(User user, Team team, Role role) {
		List<Job> jobs = user.getJobs();
		for (int i = 0; i < jobs.size(); i++) {
			if (jobs.get(i).getTeam().equals(team) && jobs.get(i).getRole().equals(role)) {
				Job job = jobs.get(i);
				jobs.remove(i);
				userDAO.save(user);
				//jobDAO.deleteById(job.getId());
				break;
			}
		}
		return user;

	}

	@Override
	public User updateJob(User user, Team team, Role role) {
		List<Job> jobs = user.getJobs();
		for (int i = 0; i < jobs.size(); i++) {
			if (jobs.get(i).getTeam().equals(team)) {
				Job job = jobs.get(i);
				job.setRole(role);
				jobs.remove(i);
				jobs.add(job);
				user.setJobs(jobs);
				userDAO.save(user);
				break;
			}
		}
		return user;
	}

}
