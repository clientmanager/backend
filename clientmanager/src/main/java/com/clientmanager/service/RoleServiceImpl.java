package com.clientmanager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.PermissionDAO;
import com.clientmanager.dao.RoleDAO;
import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Permission.PermissionType;
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
	public Role getRoleById(int id) throws BusinessExcpetion {
		if (!roleDAO.existsById(id)) {
			throw new BusinessExcpetion("role not found");
		}
		Role role = roleDAO.findById(id).orElse(null);
		if (role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		return role;
	}

	@Transactional
	@Override
	public Role createRole(Role role) throws BusinessExcpetion {
		if (role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if (roleDAO.existsById(role.getId())) {
			throw new BusinessExcpetion("duplicate role found");
		}
		return roleDAO.save(role);
	}

	@Override
	public Role updateRole(Role role) throws BusinessExcpetion {
		if (role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if (!roleDAO.existsById(role.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		Role role2 = roleDAO.findById(role.getId()).orElse(null);
		role2.setActive(role.isActive());
		role2.setDescription(role.getDescription());
		role2.setRolename(role.getRolename());
		role2.setRoletype(role.getRoletype());
//		Set<Permission> savepermissions = role.getRolepermissions();
//		role.setRolepermissions(new HashSet<Permission>());
		Role ret = roleDAO.save(role2);
//		ret.setRolepermissions(savepermissions);
		return ret;
	}

	@Override
	public void deleteRole(Role role) throws BusinessExcpetion {
		if (role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if (!roleDAO.existsById(role.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		Set<Permission> permissions = new HashSet<Permission>();
		role.setRolepermissions(permissions);
		roleDAO.save(role);
		roleDAO.delete(role);
	}

	@Override
	public Role addPermission(Role role, Permission permission) throws BusinessExcpetion {
		if (role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if (permission == null || !permission.validate() || permission.getPermissiontype() != PermissionType.ROLE) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (!roleDAO.existsById(role.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		if (!permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("permission not found");
		}
		Set<Permission> permissions = role.getRolepermissions();
		permissions.add(permission);
		role.setRolepermissions(permissions);
		roleDAO.save(role);
		return role;
	}

	@Override
	public Role removePermission(Role role, Permission permission) throws BusinessExcpetion {
		role = roleDAO.findById(role.getId()).orElse(null);
		permission = permissionDAO.findById(permission.getId()).orElse(null);
		System.out.println("+++++++++++++++++++\n\nRemovePermission - 1");
		if (role == null || !role.validate()) {
			throw new BusinessExcpetion("invalid role");
		}
		if (permission == null || !permission.validate() || permission.getPermissiontype() != PermissionType.ROLE) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (!roleDAO.existsById(role.getId())) {
			throw new BusinessExcpetion("role not found");
		}
		if (!permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("permission not found");
		}
		System.out.println("RemovePermission - 2");
		boolean found = false;
		for (Permission perm : role.getRolepermissions()) {
			if (perm.getId() == permission.getId()) {
				found = true;
			}
		}
		if (!found) {
			throw new BusinessExcpetion("permission not a member of role");
		}
		System.out.println("RemovePermission - 3");
		Set<Permission> permissions = role.getRolepermissions();
		System.out.println(permissions);
		permissions.remove(permission);
		System.out.println(permissions);
		role.setRolepermissions(permissions);
		System.out.println(role);
		roleDAO.save(role);
		System.out.println(roleDAO.findById(role.getId()));
		System.out.println("removePermission - 4");
		return role;
	}

}
