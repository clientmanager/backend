package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.GroupDAO;
import com.clientmanager.model.Group;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupDAO groupDAO;
	
	@Transactional
	@Override
	public List<Group> getAllGroups() {
		return groupDAO.findAll();
	}

	@Transactional
	@Override
	public Optional<Group> getGroupById(int id) {
		return groupDAO.findById(id);
	}

	@Transactional
	@Override
	public Group createGroup(Group group) {
		return groupDAO.save(group);
	}

	@Override
	public Group updateGroup(Group group) {
		if(groupDAO.existsById(group.getGroup_id())) {
			return groupDAO.save(group);
		} else {
			return null;
		}
	}

	@Override
	public void deleteGroup(int id) {		
		groupDAO.deleteById(id);
	}


}
