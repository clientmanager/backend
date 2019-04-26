package com.clientmanager.service;

import java.util.List;

import com.clientmanager.model.Permission;
import com.clientmanager.model.Team;

public interface TeamService {
	public List<Team> getAllTeams();
	public Team getTeamById(int id);
	public Team createTeam(Team team);
	public Team updateTeam(Team team);
	public void deleteTeam(Team team);
	
	public Team addPermission(Team team, Permission permission);
	public Team removePermission(Team team, Permission permission);
}
