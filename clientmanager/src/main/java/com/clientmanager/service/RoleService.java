package com.clientmanager.service;

import java.util.List;

import com.clientmanager.model.Permission;
import com.clientmanager.model.Role;

public interface RoleService {
	public List<Role> getAllRoles();
	public Role getRoleById(int id);
	public Role createRole(Role role);
	public Role updateRole(Role role);
	public void deleteRole(Role role);
	
	public Role addPermission(Role role, Permission permission);
	public Role removePermission(Role role, Permission permission);
}
