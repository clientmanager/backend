package com.clientmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.model.Job;
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

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/user/{id}/{role_id}/{team_id}")
	public User createJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		userService.addJob(id, teamService.getTeamById(team_id).orElse(null),
				roleService.getRoleById(role_id).orElse(null));
		return userService.getUserById(id).orElse(null);
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	// permissions
	
	@GetMapping("/permission")
	public List<Permission> getAllPermissions() {
		return permissionService.getAllPermissions();
	}

	@PostMapping("/permission")
	public Permission createPermission(@RequestBody Permission permission) {
		return permissionService.createPermission(permission);
	}
	
	// roles

	@GetMapping("/role")
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@GetMapping("/role/{id}")
	public Optional<Role> getRoleById(@PathVariable int id) {
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
		roleService.deleteRole(id);
	}
	
	@PostMapping("/role/{id}/{permissionId}")
	public Role addRolePermission(@PathVariable int id, @PathVariable int permissionId) {
		roleService.addPermission(id, permissionId);
		return roleService.getRoleById(id).orElse(null);
	}
	

	// team
	
	@GetMapping("/team")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping("/team/{id}")
	public Optional<Team> getTeamById(@PathVariable int id) {
		return teamService.getTeamById(id);
	}

	@PostMapping("/team")
	public Team createTeam(@RequestBody Team team) {
		return teamService.createTeam(team);
	}
	
	@PostMapping("/team/{id}/{permissionId}")
	public Team addTeamPermission(@PathVariable int id, @PathVariable int permissionId) {
		teamService.addPermission(id, permissionId);
		return teamService.getTeamById(id).orElse(null);
	}

	@PutMapping("/team")
	public Team updateTeam(@RequestBody Team team) {
		return teamService.updateTeam(team);
	}

	@DeleteMapping("/team/{id}")
	public void deleteTeam(@PathVariable int id) {
		teamService.deleteTeam(id);
	}

}
