package com.clientmanager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Group")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id")
	private int group_id;

	@Column(name = "groupname", unique = true)
	@NotNull
	private String groupname;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "active")
	private boolean active;

	@Column(name = "grouppermissions")
	@ManyToMany()
	@JoinTable(name = "group_permissions", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> grouppermissions;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Group(int group_id, @NotNull String groupname, @NotNull String description, boolean active,
			List<Permission> grouppermissions) {
		this.group_id = group_id;
		this.groupname = groupname;
		this.description = description;
		this.active = active;
		this.grouppermissions = grouppermissions;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
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

	public List<Permission> getGrouppermissions() {
		return grouppermissions;
	}

	public void setGrouppermissions(List<Permission> grouppermissions) {
		this.grouppermissions = grouppermissions;
	}

	@Override
	public String toString() {
		return "Group [group_id=" + group_id + ", groupname=" + groupname + ", description=" + description + ", active="
				+ active + ", grouppermissions=" + grouppermissions + "]";
	}

}
