package com.clientmanager;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Permission.PermissionType;
import com.clientmanager.model.Role;
import com.clientmanager.model.Role.RoleType;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;
import com.clientmanager.service.PermissionService;
import com.clientmanager.service.RoleService;
import com.clientmanager.service.TeamService;
import com.clientmanager.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientmanagerApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TeamService teamService;

	@Test
	public void contextLoads() {
	}

	User user, user2;
	Team team, team2;
	Role role, role2;
	Permission permission, permission2, permission3, permission4;

	@Before
	public void before() {
		user = new User();
		user.setUsername("user123");
		user.setPassword("123");
		user.setFname("Michael");
		user.setLname("Jackson");
		user.setGender('M');
		user2 = new User();
		user2.setUsername("user124");
		user2.setPassword("124");
		user2.setFname("Billy");
		user2.setLname("Jean");
		user2.setGender('F');
		team = new Team();
		team.setGroupname("Smooth Criminals");
		team.setDescription("Not his lover.");
		team.setActive(true);
		team2 = new Team();
		team2.setGroupname("Violent Gang");
		team2.setDescription("They're bad.");
		team2.setActive(false);
		role = new Role();
		role.setRolename("Singer");
		role.setDescription("AHHHH!");
		role.setActive(false);
		role.setRoletype(RoleType.PROJECT);
		role2 = new Role();
		role2.setRolename("Girl");
		role2.setDescription("Claims she is the one.");
		role2.setActive(true);
		role2.setRoletype(RoleType.SYSTEM);
		permission = new Permission();
		permission.setPermissionname("Role - Level 1");
		permission.setDescription("Lowest level role permission");
		permission.setPermissiontype(PermissionType.ROLE);
		permission2 = new Permission();
		permission2.setPermissionname("Role - Level 2");
		permission2.setDescription("Highest level role permission");
		permission2.setPermissiontype(PermissionType.ROLE);
		permission3 = new Permission();
		permission3.setPermissionname("Team - Level 1");
		permission3.setDescription("Lowest level team permission");
		permission3.setPermissiontype(PermissionType.GROUP);
		permission4 = new Permission();
		permission4.setPermissionname("Team - Level 2");
		permission4.setDescription("Highest level team permission");
		permission4.setPermissiontype(PermissionType.GROUP);
		try {
			System.out.println("before - 1");
			// Create User
			user = userService.createUser(user);
			user2 = userService.createUser(user2);
			System.out.println("before - 2");
			// Create Team
			team = teamService.createTeam(team);
			System.out.println("before - 2.5");
			team2 = teamService.createTeam(team2);
			System.out.println("before - 3");
			// Create Role
			role = roleService.createRole(role);
			role2 = roleService.createRole(role2);
			System.out.println("before - 4");
			// Create Permission
			permission = permissionService.createPermission(permission);
			permission2 = permissionService.createPermission(permission2);
			permission3 = permissionService.createPermission(permission3);
			permission4 = permissionService.createPermission(permission4);
			System.out.println("before - 5");
			System.out.println(user + "\n" + team + "\n" + role + "\n");
			// User - Add Job
			// Nick, your job is to fix this:
			System.out.println("before - 6");
			// Team - Add Permission
			team = teamService.addPermission(team, permission3);
			team2 = teamService.addPermission(team2, permission4);
			System.out.println("before - 7");
			// Role - Add Permission
			role = roleService.addPermission(role, permission);
			role2 = roleService.addPermission(role2, permission2);
			System.out.println("before - 8");
			System.out.println(userService.getAllUsers() + "\n" + teamService.getAllTeams() + "\n" + roleService.getAllRoles());
			user = userService.addJob(user, team, role);
			user2 = userService.addJob(user2, team2, role2);
			System.out.println("before - 9");
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		System.out.println("----------------------TEST END--------------------------");
		// Delete User
		List<User> users = userService.getAllUsers();
		for (int i = 0; i < users.size(); i++) {
			try {
				userService.deleteUser(users.get(i));
			} catch (BusinessExcpetion e) {
				e.printStackTrace();
			}
		}
		System.out.println("after - 1");
		// Delete Team
		List<Team> teams = teamService.getAllTeams();
		for (int i = 0; i < teams.size(); i++) {
			try {
				System.out.println("after - 1.5");
				teamService.deleteTeam(teams.get(i));
				System.out.println("after - 1.7");
			} catch (BusinessExcpetion e) {
				e.printStackTrace();
			}
		}
		System.out.println("after - 2");
		// Delete Role
		List<Role> roles = roleService.getAllRoles();
		for (int i = 0; i < roles.size(); i++) {
			try {
				roleService.deleteRole(roles.get(i));
			} catch (BusinessExcpetion e) {
				e.printStackTrace();
			}
		}
		System.out.println("after - 3");
		// Delete Permission
		List<Permission> permissions = permissionService.getAllPermissions();
		for (int i = 0; i < permissions.size(); i++) {
			try {
				permissionService.deletePermission(permissions.get(i));
			} catch (BusinessExcpetion e) {
				e.printStackTrace();
			}
		}
		System.out.println("after - 4");
		user = null;
		user2 = null;
		team = null;
		team2 = null;
		role = null;
		role2 = null;
		permission = null;
		permission2 = null;
		permission3 = null;
		permission4 = null;
		System.out.println("after - 5");
	}

	@Test
	public void testReadUser() {
		try {
			assertTrue(user.equals(userService.getUserById(user.getId())));
			assertTrue(userService.getUserById(user.getId()).equals(user));
			assertTrue(user2.equals(userService.getUserById(user2.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadTeam() {
		try {
			assertTrue(team.equals(teamService.getTeamById(team.getId())));
			assertTrue(team2.equals(teamService.getTeamById(team2.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadRole() {
		try {
			assertTrue(role.equals(roleService.getRoleById(role.getId())));
			assertTrue(role2.equals(roleService.getRoleById(role2.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadPermission() {
		try {
			assertTrue(permission.equals(permissionService.getPermissionById(permission.getId())));
			assertTrue(permission2.equals(permissionService.getPermissionById(permission2.getId())));
			assertTrue(permission3.equals(permissionService.getPermissionById(permission3.getId())));
			assertTrue(permission4.equals(permissionService.getPermissionById(permission4.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateUser() {
		try {
			int oldid = user.getId();
			user.setMname("new middlename");
			user = userService.updateUser(user);
			System.out.println(user);
			System.out.println(userService.getUserById(user.getId()));
			assertTrue(oldid == user.getId());
			assertTrue(user.equals(userService.getUserById(user.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTeam() {
		try {
			int oldid = team.getId();
			team.setDescription("new description");
			team = teamService.updateTeam(team);
			System.out.println(team);
			System.out.println(teamService.getTeamById(team.getId()));
			assertTrue(oldid == team.getId());
			assertTrue(team.equals(teamService.getTeamById(team.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateRole() {
		try {
			int oldid = role.getId();
			role.setDescription("new description");
			role = roleService.updateRole(role);
			assertTrue(oldid == role.getId());
			assertTrue(role.equals(roleService.getRoleById(role.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdatePermission() {
		try {
			int oldid = permission.getId();
			permission.setPermissionname("new permissionname");
			permission = permissionService.updatePermission(permission);
			assertTrue(oldid == permission.getId());
			assertTrue(permission.equals(permissionService.getPermissionById(permission.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateJob() {
		try {
			int oldid = user.getId();
			user = userService.updateJob(user, team, role2);
			assertTrue(oldid == user.getId());
			assertTrue(user.equals(userService.getUserById(user.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveJob() {
		try {
			int oldid = user.getId();
			user = userService.removeJob(user, team, role);
			assertTrue(oldid == user.getId());
			assertTrue(user.equals(userService.getUserById(user.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteTeamPermission() {
		try {
			int oldid = team.getId();
			team = teamService.removePermission(team, permission4);
			System.out.println(team);
			System.out.println(teamService.getTeamById(team.getId()));
			assertTrue(oldid == team.getId());
			assertTrue(team.equals(teamService.getTeamById(team.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteRolePermission() {
		try {
			int oldid = role.getId();
			role = roleService.removePermission(role, permission);
			System.out.println("------------------------------xx--------------------------------");
			System.out.println(role);
			System.out.println(roleService.getRoleById(role.getId()));
			assertTrue(oldid == role.getId());
			assertTrue(role.equals(roleService.getRoleById(role.getId())));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	// test for bad input
	
	@Test
	public void testCreateUserBadInput() throws BusinessExcpetion {
		user.setFname("");
		assertThrows(BusinessExcpetion.class, () -> userService.createUser(user));
	}

	@Test
	public void testCreateTeamBadInput() throws BusinessExcpetion {
		team.setGroupname("");
		assertThrows(BusinessExcpetion.class, () -> teamService.createTeam(team));
	}

	@Test
	public void testCreateRoleBadInput() throws BusinessExcpetion {
		role.setRolename("");
		assertThrows(BusinessExcpetion.class, () -> roleService.createRole(role));
	}

	@Test
	public void testCreatePermissionBadInput() throws BusinessExcpetion {
		permission.setPermissionname("");
		assertThrows(BusinessExcpetion.class, () -> permissionService.createPermission(permission));
	}
	
	@Test
	public void testUpdateUserBadInput() throws BusinessExcpetion {
		user.setFname("");
		assertThrows(BusinessExcpetion.class, () -> userService.updateUser(user));
	}
	
	@Test
	public void testUpdateTeamBadInput() throws BusinessExcpetion {
		team.setGroupname("");
		assertThrows(BusinessExcpetion.class, () -> teamService.updateTeam(team));
	}
	
	@Test
	public void testUpdateRoleBadInput() throws BusinessExcpetion {
		role.setRolename("");
		assertThrows(BusinessExcpetion.class, () -> roleService.updateRole(role));
	}
	
	@Test
	public void testUpdatePermissionBadInput() throws BusinessExcpetion {
		permission.setPermissionname("");
		assertThrows(BusinessExcpetion.class, () -> permissionService.updatePermission(permission));
	}
	
	// test for non-existent records
	// using id 0 for everything because its valid and will never be used
	
	@Test
	public void testReadUserByIdNoUser() throws BusinessExcpetion {
		assertThrows(BusinessExcpetion.class, () -> userService.getUserById(0));
	}
	
	@Test
	public void testReadTeamByIdNoTeam() throws BusinessExcpetion {
		assertThrows(BusinessExcpetion.class, () -> teamService.getTeamById(0));
	}
	
	@Test
	public void testReadRoleByIdNoRole() throws BusinessExcpetion {
		assertThrows(BusinessExcpetion.class, () -> roleService.getRoleById(0));
	}
	
	@Test
	public void testReadPermissionByIdNoPermission() throws BusinessExcpetion {
		assertThrows(BusinessExcpetion.class, () -> permissionService.getPermissionById(0));
	}
	
	@Test
	public void testUpdateUserNoUser() throws BusinessExcpetion {
		user.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.updateUser(user));
	}
	
	@Test
	public void testUpdateTeamNoTeam() throws BusinessExcpetion {
		team.setId(0);
		assertThrows(BusinessExcpetion.class, () -> teamService.updateTeam(team));
	}
	
	@Test
	public void testUpdateRoleNoRole() throws BusinessExcpetion {
		role.setId(0);
		assertThrows(BusinessExcpetion.class, () -> roleService.updateRole(role));
	}
	
	@Test
	public void testUpdatePermissonNoPermission() throws BusinessExcpetion {
		permission.setId(0);
		assertThrows(BusinessExcpetion.class, () -> permissionService.updatePermission(permission));
	}
	
	@Test
	public void testDeleteUserNoUser() throws BusinessExcpetion {
		user.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.deleteUser(user));
		
	}
	
	@Test
	public void testDeleteTeamNoTeam() throws BusinessExcpetion {
		team.setId(0);
		assertThrows(BusinessExcpetion.class, () -> teamService.deleteTeam(team));
	}
	
	@Test
	public void testDeleteRoleNoRole() throws BusinessExcpetion {
		role.setId(0);
		assertThrows(BusinessExcpetion.class, () -> roleService.deleteRole(role));
	}
	
	@Test
	public void testDeletePermissionNoPermission() throws BusinessExcpetion {
		permission.setId(0);
		assertThrows(BusinessExcpetion.class, () -> permissionService.deletePermission(permission));
	}
	
	@Test
	public void testUserAddJobNoUser() throws BusinessExcpetion {
		user.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.addJob(user, team2, role2));
	}
	
	@Test
	public void testUserAddJobNoTeam() throws BusinessExcpetion {
		team2.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.addJob(user, team2, role2));
	}
	
	@Test
	public void testUserAddJobNoRole() throws BusinessExcpetion {
		role2.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.addJob(user, team2, role2));
	}
	
	@Test
	public void testTeamAddPermissionNoTeam() throws BusinessExcpetion {
		team.setId(0);
		assertThrows(BusinessExcpetion.class, () -> teamService.addPermission(team, permission4));
	}
	
	@Test
	public void testTeamAddPermissionNoPermission() throws BusinessExcpetion {
		permission4.setId(0);
		assertThrows(BusinessExcpetion.class, () -> teamService.addPermission(team, permission4));
	}
	
	@Test
	public void testRoleAddPermissionNoRole() throws BusinessExcpetion {
		role.setId(0);
		assertThrows(BusinessExcpetion.class, () -> roleService.addPermission(role, permission2));
	}
	
	@Test
	public void testRoleAddPermissionNoPermission() throws BusinessExcpetion {
		permission2.setId(0);
		assertThrows(BusinessExcpetion.class, () -> roleService.addPermission(role, permission2));
	}
	
	@Test
	public void testUserUpdateJobNoUser() throws BusinessExcpetion {
		user.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.updateJob(user, team, role2));
	}
	
	@Test
	public void testUserUpdateJobNoTeam() throws BusinessExcpetion {
		team.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.updateJob(user, team, role2));
	}
	
	@Test
	public void testUserUpdateJobNoRole() throws BusinessExcpetion {
		role2.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.updateJob(user, team, role2));
	}
	
	@Test
	public void testUserRemoveJobNoUser() throws BusinessExcpetion {
		user.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.removeJob(user, team, role));
	}
	
	@Test
	public void testUserRemoveJobNoTeam() throws BusinessExcpetion {
		team.setId(0);
		assertThrows(BusinessExcpetion.class, () -> userService.removeJob(user, team, role));
	}
	
	@Test
	public void testTeamRemovePermissionNoTeam() throws BusinessExcpetion {
		team.setId(0);
		assertThrows(BusinessExcpetion.class, () -> teamService.removePermission(team, permission3));
	}
	
	@Test
	public void testTeamRemovePermissionNoPermission() throws BusinessExcpetion {
		permission3.setId(0);
		assertThrows(BusinessExcpetion.class, () -> teamService.removePermission(team, permission3));
	}
	
	@Test
	public void testRoleRemovePermissionNoRole() throws BusinessExcpetion {
		role.setId(0);
		assertThrows(BusinessExcpetion.class, () -> roleService.removePermission(role, permission));
	}
	
	@Test
	public void testRoleRemovePermissionNoPermission() throws BusinessExcpetion {
		permission.setId(0);
		assertThrows(BusinessExcpetion.class, () -> roleService.removePermission(role, permission));
	}

}
