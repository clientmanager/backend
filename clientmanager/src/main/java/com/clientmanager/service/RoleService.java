package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.Role;
import com.clientmanager.model.Role;

public interface RoleService {
	public List<Role> getAllRoles();
	public Optional<Role> getRoleById(int id);
	public Role createRole(Role role);
	public Role updateRole(Role role);
	public void deleteRole(int id);
	
	public void addPermission(int roleId, int permissionId);
	public void removePermission(int roleId, int permissonId);
}
