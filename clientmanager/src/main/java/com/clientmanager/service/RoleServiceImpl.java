package com.clientmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.PermissionDAO;
import com.clientmanager.dao.RoleDAO;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Role;
import com.clientmanager.model.Team;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	PermissionDAO permissionDAO;
	
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
	
	@Override
	public void addPermission(int roleId, int permissionId) {
		Permission permission = permissionDAO.findById(permissionId).orElse(null);
		Role role = roleDAO.findById(roleId).orElse(null);
		Set<Permission> permissions = role.getRolepermissions();
		permissions.add(permission);
		role.setRolepermissions(permissions);;
		roleDAO.save(role);
	}

	@Override
	public void removePermission(int teamId, int permissonId) {
		Role role = roleDAO.findById(teamId).orElse(null);
		List<Permission> permissions = (List<Permission>) role.getRolepermissions();
		for(int i = 0; i < permissions.size(); i++) {
			if(permissions.get(i).getId() == permissonId) {
				permissions.remove(i);
			}
		}
	}

}
