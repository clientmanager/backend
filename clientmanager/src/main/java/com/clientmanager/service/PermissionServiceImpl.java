package com.clientmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.PermissionDAO;
import com.clientmanager.exception.BusinessExcpetion;
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
	public Permission getPermissionById(int id) throws BusinessExcpetion {
		if (!permissionDAO.existsById(id)) {
			throw new BusinessExcpetion("permission not found");
		}
		Permission permission = permissionDAO.findById(id).orElse(null);
		if (permission == null || !permission.validate()) {
			throw new BusinessExcpetion("invalid permission");
		}
		return permission;
	}

	@Transactional
	@Override
	public Permission createPermission(Permission permission) throws BusinessExcpetion {
		if (permission == null || !permission.validate()) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("duplicate permission found");
		}
		return permissionDAO.save(permission);
	}

	@Override
	public Permission updatePermission(Permission permission) throws BusinessExcpetion {
		if (permission == null || !permission.validate()) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (!permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("permission not found");
		}
		return permissionDAO.save(permission);
	}

	@Override
	public void deletePermission(Permission permission) throws BusinessExcpetion {
		if (permission == null || !permission.validate()) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (!permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("permission not found");
		}
		permissionDAO.delete(permission);
	}

}
