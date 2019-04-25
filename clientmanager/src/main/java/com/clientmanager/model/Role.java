package com.clientmanager.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Role")
public class Role {

	public enum RoleType {
		SYSTEM, PROJECT
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "rolename", unique = true)
	@NotNull
	private String rolename;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "active")
	private boolean active;

	@Column(name = "roletype")
	@NotNull
	private RoleType roletype;

	@ManyToMany()
	private Set<Permission> rolepermissions;

	public Role() {
		rolepermissions = new HashSet<>();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, @NotNull String rolename, @NotNull String description, boolean active,
			@NotNull RoleType roletype, Set<Permission> rolepermissions) {
		this.id = id;
		this.rolename = rolename;
		this.description = description;
		this.active = active;
		this.roletype = roletype;
		this.rolepermissions = rolepermissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public RoleType getRoletype() {
		return roletype;
	}

	public void setRoletype(RoleType roletype) {
		this.roletype = roletype;
	}

	public Set<Permission> getRolepermissions() {
		return rolepermissions;
	}

	public void setRolepermissions(Set<Permission> rolepermissions) {
		this.rolepermissions = rolepermissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", description=" + description + ", active=" + active
				+ ", roletype=" + roletype + ", rolepermissions=" + rolepermissions + "]";
	}

}
