package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.Group;

public interface GroupService {
	public List<Group> getAllGroups();
	public Optional<Group> getGroupById(int id);
	public Group createGroup(Group group);
	public Group updateGroup(Group group);
	public void deleteGroup(int id);
	
}
