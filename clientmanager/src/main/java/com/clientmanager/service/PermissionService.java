package com.clientmanager.service;

import java.util.List;

import com.clientmanager.model.Permission;

public interface PermissionService {
	public List<Permission> getAllPermissions();
	public Permission getPermissionById(int id);
	public Permission createPermission(Permission permission);
	public Permission updatePermission(Permission permission);
	public void deletePermission(Permission permission);
	
}
