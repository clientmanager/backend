package com.clientmanager.service;

import java.util.List;

import com.clientmanager.exception.BusinessExcpetion;
import com.clientmanager.model.Permission;
import com.clientmanager.model.Team;

public interface TeamService {
	public List<Team> getAllTeams();
	public Team getTeamById(int id) throws BusinessExcpetion;
	public Team createTeam(Team team) throws BusinessExcpetion;
	public Team updateTeam(Team team) throws BusinessExcpetion;
	public void deleteTeam(Team team) throws BusinessExcpetion;
	
	public Team addPermission(Team team, Permission permission) throws BusinessExcpetion;
	public Team removePermission(Team team, Permission permission) throws BusinessExcpetion;
}
