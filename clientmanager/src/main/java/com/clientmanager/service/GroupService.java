package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.Group_;

public interface GroupService {
	public List<Group_> getAllGroup_s();
	public Optional<Group_> getGroup_ById(int id);
	public Group_ createGroup_(Group_ group);
	public Group_ updateGroup_(Group_ group);
	public void deleteGroup_(int id);
	
}
