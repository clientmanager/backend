package com.clientmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Role;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;
import com.clientmanager.service.PermissionService;
import com.clientmanager.service.RoleService;
import com.clientmanager.service.TeamService;
import com.clientmanager.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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
	
	@GetMapping("/user/search/lname/{lname}")
	public List<User> filterUsersByName(@PathVariable String lname) {
		return userService.filterUsersByName(lname);
	}

	@GetMapping("/user/search/teamName/{teamName}")
	public List<User> filterUsersByTeam(@PathVariable String teamName) {
		return userService.filterUsersByTeam(teamName);
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id) {
		try {
			return userService.getUserById(id);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		try {
			return userService.createUser(user);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		try {
			return userService.updateUser(user);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		try {
			userService.deleteUser(userService.getUserById(id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	// Jobs--------------------------------------------------------

	@PostMapping("/user/{id}/{team_id}/{role_id}")
	public User createJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		try {
			return userService.addJob(userService.getUserById(id), teamService.getTeamById(team_id),
					roleService.getRoleById(role_id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/user/removejob/{id}/{team_id}/{role_id}")
	public User removeJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		try {
			return userService.removeJob(userService.getUserById(id), teamService.getTeamById(team_id),
					roleService.getRoleById(role_id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/user/updatejob/{id}/{team_id}/{role_id}")
	public User updateJob(@PathVariable int id, @PathVariable int team_id, @PathVariable int role_id) {
		try {
			return userService.updateJob(userService.getUserById(id), teamService.getTeamById(team_id),
					roleService.getRoleById(role_id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	// Permissions--------------------------------------------------------

	@GetMapping("/permission")
	public List<Permission> getAllPermissions() {
		return permissionService.getAllPermissions();
	}

	@PostMapping("/permission")
	public Permission createPermission(@RequestBody Permission permission) {
		try {
			return permissionService.createPermission(permission);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PutMapping("/permission")
	public Permission updatePermission(@RequestBody Permission permission) {
		try {
			return permissionService.updatePermission(permission);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/permission/{id}")
	public void deletePermission(@PathVariable int id) {
		try {
			permissionService.deletePermission(permissionService.getPermissionById(id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	// Roles--------------------------------------------------------

	@GetMapping("/role")
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable int id) {
		try {
			return roleService.getRoleById(id);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/role")
	public Role createRole(@RequestBody Role role) {
		try {
			return roleService.createRole(role);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PutMapping("/role")
	public Role updateRole(@RequestBody Role role) {
		try {
			return roleService.updateRole(role);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/role/{id}")
	public void deleteRole(@PathVariable int id) {
		try {
			roleService.deleteRole(roleService.getRoleById(id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/role/{id}/{permissionId}")
	public Role addRolePermission(@PathVariable int id, @PathVariable int permissionId) {
		try {
			return roleService.addPermission(roleService.getRoleById(id),
					permissionService.getPermissionById(permissionId));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/role/removepermission/{id}/{permissionId}")
	public Role removeRolePermission(@PathVariable int id, @PathVariable int permissionId) {
		try {
			return roleService.removePermission(roleService.getRoleById(id),
					permissionService.getPermissionById(permissionId));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	// Team--------------------------------------------------------

	@GetMapping("/team")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping("/team/{id}")
	public Team getTeamById(@PathVariable int id) {
		try {
			return teamService.getTeamById(id);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/team")
	public Team createTeam(@RequestBody Team team) {
		try {
			return teamService.createTeam(team);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PutMapping("/team")
	public Team updateTeam(@RequestBody Team team) {
		try {
			return teamService.updateTeam(team);
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/team/{id}")
	public void deleteTeam(@PathVariable int id) {
		try {
			teamService.deleteTeam(teamService.getTeamById(id));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/team/{id}/{permissionId}")
	public Team addTeamPermission(@PathVariable int id, @PathVariable int permissionId) {
		try {
			System.out.println("pre add permission");
			return teamService.addPermission(teamService.getTeamById(id), permissionService.getPermissionById(permissionId));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/team/removepermission/{id}/{permissionId}")
	public Team removeTeamPermission(@PathVariable int id, @PathVariable int permissionId) {
		try {
			return teamService.removePermission(teamService.getTeamById(id), permissionService.getPermissionById(permissionId));
		} catch (BusinessExcpetion e) {
			e.printStackTrace();
		}
		return null;
	}

}
