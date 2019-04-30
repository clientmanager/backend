package com.clientmanager.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Job")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@NotNull
	private Team team;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	private Role role;

	public Job() {

	}

	public Job(@NotNull Team team, @NotNull Role role) {
		this.team = team;
		this.role = role;
	}

	public Job(int id, @NotNull Team team, @NotNull Role role) {
		this.id = id;
		this.team = team;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", team=" + team + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
	
	public boolean validate() {
		System.out.println("validate job - 1");
		boolean ret = true;
		if (id < 0) {
			ret = false;
		}
		if(team == null) {
			ret = false;
		}
		if(role == null) {
			ret = false;
		}
		System.out.println("validate job - 2");
		return ret;
	}

}
