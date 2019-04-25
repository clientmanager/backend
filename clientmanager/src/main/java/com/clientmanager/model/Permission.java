package com.clientmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Permission")
public class Permission {

	public enum PermissionType {
		ROLE, GROUP
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "permissionname", unique = true)
	@NotNull
	private String permissionname;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "active")
	private boolean active;

	@Column(name = "permissiontype")
	@NotNull
	private PermissionType PrmissionType;

	public Permission() {
		// TODO Auto-generated constructor stub
	}

	public Permission(int id, @NotNull String permissionname, @NotNull String description, boolean active,
			@NotNull PermissionType prmissionType) {
		this.id = id;
		this.permissionname = permissionname;
		this.description = description;
		this.active = active;
		PrmissionType = prmissionType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
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

	public PermissionType getPrmissionType() {
		return PrmissionType;
	}

	public void setPrmissionType(PermissionType prmissionType) {
		PrmissionType = prmissionType;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionname=" + permissionname + ", description=" + description
				+ ", active=" + active + ", PrmissionType=" + PrmissionType + "]";
	}



}
