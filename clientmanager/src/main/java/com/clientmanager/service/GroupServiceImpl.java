package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.GroupDAO;
import com.clientmanager.model.Group_;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupDAO groupDAO;
	
	@Transactional
	@Override
	public List<Group_> getAllGroup_s() {
		return groupDAO.findAll();
	}

	@Transactional
	@Override
	public Optional<Group_> getGroup_ById(int id) {
		return groupDAO.findById(id);
	}

	@Transactional
	@Override
	public Group_ createGroup_(Group_ group) {
		return groupDAO.save(group);
	}

	@Override
	public Group_ updateGroup_(Group_ group) {
		if(groupDAO.existsById(group.getId())) {
			return groupDAO.save(group);
		} else {
			return null;
		}
	}

	@Override
	public void deleteGroup_(int id) {		
		groupDAO.deleteById(id);
	}


}
