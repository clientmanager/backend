package com.clientmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.JobDAO;
import com.clientmanager.dao.RoleDAO;
import com.clientmanager.dao.TeamDAO;
import com.clientmanager.dao.UserDAO;
import com.clientmanager.exception.BusinessExcpetion;
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
	
	@Autowired
	TeamDAO teamDAO;

	@Autowired
	RoleDAO roleDAO;


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
	public List<User> filterUsersByTeam(String teamName) {
		List<User> users = userDAO.findAll();
		List<User> matched = new ArrayList<>();
		for(int x = 0; x < users.size(); x++) {
			List<Job> jobs = users.get(x).getJobs();
			for (int y = 0; y < jobs.size(); y ++) {
				if(jobs.get(y).getTeam().getGroupname().equals(teamName)) {
					matched.add(users.get(x));
					break;
				}
			}
		}
		return matched;
	}

	@Transactional
	@Override
	public User getUserById(int id) throws BusinessExcpetion {
		if(!userDAO.existsById(id)) {
			throw new BusinessExcpetion("user not found");
		}
		User user = userDAO.findById(id).orElse(null);
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		return user;
	}

	@Transactional
	@Override
	public User createUser(User user) throws BusinessExcpetion {
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid user");
		}
		if(userDAO.existsById(user.getId())) {
			throw new BusinessExcpetion("duplicate user found");
		}
		return userDAO.save(user);
	}

	@Transactional
	@Override
	public User updateUser(User user) throws BusinessExcpetion {
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if(!userDAO.existsById(user.getId())) {
			throw new BusinessExcpetion("user not found");
		}
		return userDAO.save(user);

	}

	@Transactional
	@Override
	public void deleteUser(User user) throws BusinessExcpetion {
		System.out.println("delete userservice - 1");
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		System.out.println("delete userservice - 2");
		if(!userDAO.existsById(user.getId())) {
			throw new BusinessExcpetion("user not found");
		}
		System.out.println("delete userservice - 3");
		userDAO.delete(user);
	}

	@Transactional
	@Override
	public List<Job> getAllJobs(User user) throws BusinessExcpetion {
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if(!userDAO.existsById(user.getId())) {
			throw new BusinessExcpetion("user not found");
		}
		return user.getJobs();
	}

	@Transactional
	@Override
	public User addJob(User user, Team team, Role role) throws BusinessExcpetion {
		System.out.println("addjob: " + user + ", " + team + ", " + role);
		user = userDAO.findById(user.getId()).orElse(null);
		team = teamDAO.findById(team.getId()).orElse(null);;
		role = roleDAO.findById(role.getId()).orElse(null);;
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		System.out.println("addjob: - 2");
		if(team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		System.out.println("addjob: - 3");
		if(role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		System.out.println("addjob: - 4");
		if(!userDAO.existsById(user.getId())) {
			throw new BusinessExcpetion("user not found");
		}
		System.out.println("addjob: - 5");
		if(!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		System.out.println("addjob: - 6");
		if(!roleDAO.existsById(role.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		System.out.println("addjob: - 7");
		List<Job> jobs = user.getJobs();
		boolean found = false;
		System.out.println("addjob: - 8");
		for (int i = 0; i < jobs.size(); i++) {
			if (jobs.get(i).getTeam().equals(team) && jobs.get(i).getRole().equals(role)) {
				found = true;
				break;
			}
		}
		System.out.println("addjob: - 9");
		if(found) {
			throw new BusinessExcpetion("duplicate group key");
		}
		System.out.println("addjob: - 10");
		Job x = new Job(team, role);
		System.out.println(x);
		Job job = jobDAO.save(x);
		System.out.println("addjob: - 11");
		jobs.add(job);
		System.out.println("addjob: - 12");
		user.setJobs(jobs);
		System.out.println("addjob - 13: " + user);
		userDAO.save(user);
		return user;
	}

	@Transactional
	@Override
	public User removeJob(User user, Team team, Role role) throws BusinessExcpetion {
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if(team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if(role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if(!userDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("user not found");
		}
		if(!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		if(!roleDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		List<Job> jobs = user.getJobs();
		boolean found = false;
		for (int i = 0; i < jobs.size(); i++) {
			if (jobs.get(i).getTeam().equals(team) && jobs.get(i).getRole().equals(role)) {
				jobs.remove(i);
				userDAO.save(user);
				found = true;
				break;
			}
		}
		if(!found) {
			throw new BusinessExcpetion("group key not found");
		}
		return user;

	}

	@Override
	public User updateJob(User user, Team team, Role role) throws BusinessExcpetion {
		if(user == null || !user.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if(team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if(role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if(!userDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("user not found");
		}
		if(!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		if(!roleDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		List<Job> jobs = user.getJobs();
		boolean found = false;
		for (int i = 0; i < jobs.size(); i++) {
			if (jobs.get(i).getTeam().equals(team)) {
				Job job = jobs.get(i);
				job.setRole(role);
				jobs.remove(i);
				jobs.add(job);
				user.setJobs(jobs);
				userDAO.save(user);
				found = true;
				break;
			}
		}
		if(!found) {
			throw new BusinessExcpetion("group key not found");
		}
		return user;
	}

}
