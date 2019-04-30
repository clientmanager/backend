package com.clientmanager;

import java.util.HashSet;
import java.util.Set;

import com.clientmanager.model.Permission;
import com.clientmanager.model.Role;
import com.clientmanager.model.Role.RoleType;

public class TestEquals {

	public static void main(String[] args) {
		Permission p1 = new Permission();
		p1.setId(7);
		p1.setPermissionname("Role - Level 1"); 
		p1.setDescription("Lowest level role permission");
		p1.setPermissiontype(Permission.PermissionType.ROLE);
		Permission p2 = new Permission();
		p2.setId(7);
		p2.setPermissionname("Role - Level 1"); 
		p2.setDescription("Lowest level role permission");
		p2.setPermissiontype(Permission.PermissionType.ROLE);
		
		Role r1 = new Role();
		r1.setId(301);
		r1.setRolename("Singer");
		r1.setDescription("AHHHH!");
		r1.setActive(false);
		r1.setRoletype(RoleType.PROJECT);
		Set<Permission> sp1 = new HashSet<Permission>();
		sp1.add(p1);
		r1.setRolepermissions(sp1);
		
		Role r2 = new Role();
		r2.setId(301);
		r2.setRolename("Singer");
		r2.setDescription("AHHHH!");
		r2.setActive(false);
		r2.setRoletype(RoleType.PROJECT);
		Set<Permission> sp2 = new HashSet<Permission>();
		sp2.add(p2);
		r2.setRolepermissions(sp2);
				

		
		if (r1.equals(r2)) {
			System.out.println("Yes Role");
		}
		if (p1.equals(p2)) {
			System.out.println("Yes Permission");
		}
	}

}
