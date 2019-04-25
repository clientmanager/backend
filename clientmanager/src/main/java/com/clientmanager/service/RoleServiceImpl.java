package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.RoleDAO;
import com.clientmanager.model.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDAO roleDAO;
	
	@Transactional
	@Override
	public List<Role> getAllRoles() {
		return roleDAO.findAll();
	}

	@Transactional
	@Override
	public Optional<Role> getRoleById(int id) {
		return roleDAO.findById(id);
	}

	@Transactional
	@Override
	public Role createRole(Role role) {
		return roleDAO.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		if(roleDAO.existsById(role.getId())) {
			return roleDAO.save(role);
		} else {
			return null;
		}
	}

	@Override
	public void deleteRole(int id) {		
		roleDAO.deleteById(id);
	}


}
