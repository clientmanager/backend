package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.Team;

public interface TeamService {
	public List<Team> getAllTeams();
	public Optional<Team> getTeamById(int id);
	public Team createTeam(Team team);
	public Team updateTeam(Team team);
	public void deleteTeam(int id);
	
	public void addPermission(int teamId, int permissionId);
	public void removePermission(int teamId, int permissonId);
}
