package com.clientmanager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.PermissionDAO;
import com.clientmanager.dao.TeamDAO;
import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Team;
import com.clientmanager.model.User;
import com.clientmanager.model.Permission.PermissionType;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamDAO teamDAO;

	@Autowired
	PermissionDAO permissionDAO;

	@Transactional
	@Override
	public List<Team> getAllTeams() {
		return teamDAO.findAll();
	}

	@Transactional
	@Override
	public Team getTeamById(int id) throws BusinessExcpetion {
		if (!teamDAO.existsById(id)) {
			throw new BusinessExcpetion("team not found");
		}
		Team team = teamDAO.findById(id).orElse(null);
		if (team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		return team;
	}

	@Transactional
	@Override
	public Team createTeam(Team team) throws BusinessExcpetion {
		System.out.println("create team: " + team);
		if (team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if (teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("duplicate team found");
		}
		return teamDAO.save(team);
	}

	@Override
	public Team updateTeam(Team team) throws BusinessExcpetion {
		if (team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if (!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		Team team2 = teamDAO.findById(team.getId()).orElse(null);
		team2.setActive(team.isActive());
		team2.setDescription(team.getDescription());
		team2.setGroupname(team.getGroupname());
//		team2.setGrouppermissions(team.getGrouppermissions());
//		Set<Permission> savepermissions = team.getGrouppermissions();
//		team.setGrouppermissions(new HashSet<Permission>());
		Team ret = teamDAO.save(team2);
//		ret.setGrouppermissions(savepermissions);
		return ret;
	}

	@Override
	public void deleteTeam(Team team) throws BusinessExcpetion {
		System.out.println("deleteteam - 1");
		if (team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		System.out.println("deleteteam - 2");
		if (!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		System.out.println("deleteteam - 3");
		teamDAO.delete(team);
	}

	@Override
	public Team addPermission(Team team, Permission permission) throws BusinessExcpetion {
		if (team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if (permission == null || !permission.validate() || permission.getPermissiontype() != PermissionType.GROUP) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		if (!permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("permission not found");
		}
		Set<Permission> permissions = team.getGrouppermissions();
		permissions.add(permission);
		team.setGrouppermissions(permissions);
		teamDAO.save(team);
		return team;
	}

	@Override
	public Team removePermission(Team team, Permission permission) throws BusinessExcpetion {
		if (team == null || !team.validate()) {
			throw new BusinessExcpetion("invalid team");
		}
		if (permission == null || !permission.validate() || permission.getPermissiontype() != PermissionType.GROUP) {
			throw new BusinessExcpetion("invalid permission");
		}
		if (!teamDAO.existsById(team.getId())) {
			throw new BusinessExcpetion("team not found");
		}
		if (!permissionDAO.existsById(permission.getId())) {
			throw new BusinessExcpetion("permission not found");
		}
		boolean found = false;
		for (Permission perm : team.getGrouppermissions()) {
			if (perm.getId() == permission.getId()) {
				found = true;
			}
		}
		if (!found) {
			throw new BusinessExcpetion("permission not a member of team");
		}
		Set<Permission> permissions = team.getGrouppermissions();
		permissions.remove(permission);
		team.setGrouppermissions(permissions);
		teamDAO.save(team);
		return team;
	}

}
