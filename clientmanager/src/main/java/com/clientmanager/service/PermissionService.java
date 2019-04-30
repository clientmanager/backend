package com.clientmanager.service;

import java.util.List;

import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;

public interface PermissionService {
	public List<Permission> getAllPermissions();
	public Permission getPermissionById(int id) throws BusinessExcpetion;
	public Permission createPermission(Permission permission) throws BusinessExcpetion;
	public Permission updatePermission(Permission permission) throws BusinessExcpetion;
	public void deletePermission(Permission permission) throws BusinessExcpetion;
	
}
