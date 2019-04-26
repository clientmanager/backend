package com.clientmanager.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.PermissionDAO;
import com.clientmanager.dao.TeamDAO;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Team;

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
	public Team getTeamById(int id) {
		return teamDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Team createTeam(Team group) {
		return teamDAO.save(group);
	}

	@Override
	public Team updateTeam(Team group) {
		if(teamDAO.existsById(group.getId())) {
			return teamDAO.save(group);
		} else {
			return null;
		}
	}

	@Override
	public void deleteTeam(Team team) {		
		teamDAO.delete(team);
	}

	@Override
	public Team addPermission(Team team, Permission permission) {
		Set<Permission> permissions = team.getGrouppermissions();
		permissions.add(permission);
		team.setGrouppermissions(permissions);
		teamDAO.save(team);
		return team;
	}

	@Override
	public Team removePermission(Team team, Permission permission) {
		Set<Permission> permissions = team.getGrouppermissions();
		permissions.remove(permission);
		team.setGrouppermissions(permissions);
		teamDAO.save(team);
		return team;
	}


}
