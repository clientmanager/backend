package com.clientmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.model.Permission;
import com.clientmanager.model.Role;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;
import com.clientmanager.service.PermissionService;
import com.clientmanager.service.RoleService;
import com.clientmanager.service.TeamService;
import com.clientmanager.service.UserService;

@RestController
public class CMController {

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TeamService teamService;

	// User--------------------------------------------------------

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/search/{lname}")
	public List<User> filterUsersByName(@PathVariable String lname) {
		return userService.filterUsersByName(lname);
	}

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(userService.getUserById(id));
	}

	// Jobs--------------------------------------------------------

	@PostMapping("/user/{id}/{team_id}/{role_id}")
	public User createJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		return userService.addJob(userService.getUserById(id), teamService.getTeamById(team_id),
				roleService.getRoleById(role_id));
	}

	@PostMapping("/user/removejob/{id}/{team_id}/{role_id}")
	public User removeJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		return userService.removeJob(userService.getUserById(id), teamService.getTeamById(team_id),
				roleService.getRoleById(role_id));
	}

	@PostMapping("/user/updatejob/{id}/{team_id}/{role_id}")
	public User updateJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		return userService.updateJob(userService.getUserById(id), teamService.getTeamById(team_id),
				roleService.getRoleById(role_id));
	}

	// Permissions--------------------------------------------------------

	@GetMapping("/permission")
	public List<Permission> getAllPermissions() {
		return permissionService.getAllPermissions();
	}

	@PostMapping("/permission")
	public Permission createPermission(@RequestBody Permission permission) {
		return permissionService.createPermission(permission);
	}

	@PutMapping("/permission")
	public Permission updatePermission(@RequestBody Permission permission) {
		return permissionService.updatePermission(permission);
	}

	@DeleteMapping("/permission/{id}")
	public void deletePermission(@PathVariable int id) {
		permissionService.deletePermission(permissionService.getPermissionById(id));
	}

	// Roles--------------------------------------------------------

	@GetMapping("/role")
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable int id) {
		return roleService.getRoleById(id);
	}

	@PostMapping("/role")
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}

	@PutMapping("/role")
	public Role updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}

	@DeleteMapping("/role/{id}")
	public void deleteRole(@PathVariable int id) {
		roleService.deleteRole(roleService.getRoleById(id));
	}

	@PostMapping("/role/{id}/{permissionId}")
	public Role addRolePermission(@PathVariable int id, @PathVariable int permissionId) {
		return roleService.addPermission(roleService.getRoleById(id),
				permissionService.getPermissionById(permissionId));
	}

	@PostMapping("/role/removepermission/{id}/{permissionId}")
	public Role removeRolePermission(@PathVariable int id, @PathVariable int permissionId) {
		return roleService.removePermission(roleService.getRoleById(id),
				permissionService.getPermissionById(permissionId));
	}

	// Team--------------------------------------------------------

	@GetMapping("/team")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping("/team/{id}")
	public Team getTeamById(@PathVariable int id) {
		return teamService.getTeamById(id);
	}

	@PostMapping("/team")
	public Team createTeam(@RequestBody Team team) {
		return teamService.createTeam(team);
	}

	@PutMapping("/team")
	public Team updateTeam(@RequestBody Team team) {
		return teamService.updateTeam(team);
	}

	@DeleteMapping("/team/{id}")
	public void deleteTeam(@PathVariable int id) {
		teamService.deleteTeam(teamService.getTeamById(id));
	}

	@PostMapping("/team/{id}/{permissionId}")
	public Team addTeamPermission(@PathVariable int id, @PathVariable int permissionId) {
		return teamService.addPermission(teamService.getTeamById(id), permissionService.getPermissionById(permissionId));
	}

	@PostMapping("/team/removepermission/{id}/{permissionId}")
	public Team removeTeamPermission(@PathVariable int id, @PathVariable int permissionId) {
		return teamService.removePermission(teamService.getTeamById(id), permissionService.getPermissionById(permissionId));
	}

}
