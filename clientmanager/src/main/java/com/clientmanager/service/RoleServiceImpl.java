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
	public Role getRoleById(int id) {
		return roleDAO.findById(id).orElse(null);
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
	public void deleteRole(Role role) {		
		roleDAO.delete(role);
	}
	
	@Override
	public Role addPermission(Role role, Permission permission) {
		Set<Permission> permissions = role.getRolepermissions();
		permissions.add(permission);
		role.setRolepermissions(permissions);
		roleDAO.save(role);
		return role;
	}

	@Override
	public Role removePermission(Role role, Permission permission) {
		Set<Permission> permissions = role.getRolepermissions();
		permissions.remove(permission);
		role.setRolepermissions(permissions);
		roleDAO.save(role);
		return role;
	}

}
