package com.clientmanager.service;

import java.util.List;
import java.util.Optional;
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
	public Optional<Team> getTeamById(int id) {
		return teamDAO.findById(id);
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
	public void deleteTeam(int id) {		
		teamDAO.deleteById(id);
	}

	@Override
	public void addPermission(int teamId, int permissionId) {
		Permission permission = permissionDAO.findById(permissionId).orElse(null);
		Team team = teamDAO.findById(teamId).orElse(null);
		Set<Permission> permissions = team.getGrouppermissions();
		permissions.add(permission);
		team.setGrouppermissions(permissions);
		teamDAO.save(team);
	}

	@Override
	public void removePermission(int teamId, int permissonId) {
		Team team = teamDAO.findById(teamId).orElse(null);
		List<Permission> permissions = (List<Permission>) team.getGrouppermissions();
		for(int i = 0; i < permissions.size(); i++) {
			if(permissions.get(i).getId() == permissonId) {
				permissions.remove(i);
			}
		}
	}


}
