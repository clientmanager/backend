package com.clientmanager.service;

import java.util.List;

import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Role;

public interface RoleService {
	public List<Role> getAllRoles();
	public Role getRoleById(int id) throws BusinessExcpetion;
	public Role createRole(Role role) throws BusinessExcpetion;
	public Role updateRole(Role role) throws BusinessExcpetion;
	public void deleteRole(Role role) throws BusinessExcpetion;
	
	public Role addPermission(Role role, Permission permission) throws BusinessExcpetion;
	public Role removePermission(Role role, Permission permission) throws BusinessExcpetion;
}
