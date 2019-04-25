package com.clientmanager.model;

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

	@ManyToOne
	@NotNull
	private Team team;

	@ManyToOne
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

}
