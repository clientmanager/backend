package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.UserDAO;
import com.clientmanager.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
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

	@Override
	public User updateUser(User user) {
		if(userDAO.existsById(user.getId())) {
			return userDAO.save(user);
		} else {
			return null;
		}
	}

	@Override
	public void deleteUser(int id) {		
		userDAO.deleteById(id);
	}


}
