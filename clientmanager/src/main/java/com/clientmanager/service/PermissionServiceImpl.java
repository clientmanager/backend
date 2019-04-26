package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.PermissionDAO;
import com.clientmanager.model.Permission;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	PermissionDAO permissionDAO;
	
	@Transactional
	@Override
	public List<Permission> getAllPermissions() {
		return permissionDAO.findAll();
	}

	@Transactional
	@Override
	public Permission getPermissionById(int id) {
		return permissionDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Permission createPermission(Permission permission) {
		return permissionDAO.save(permission);
	}

	@Override
	public Permission updatePermission(Permission permission) {
		if(permissionDAO.existsById(permission.getId())) {
			return permissionDAO.save(permission);
		} else {
			return null;
		}
	}

	@Override
	public void deletePermission(Permission permission) {		
		permissionDAO.delete(permission);
	}


}
