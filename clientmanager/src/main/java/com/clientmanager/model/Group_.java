package com.clientmanager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "Group_")
public class Group_ {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "groupname", unique = true)
	@NotNull
	private String groupname;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "active")
	private boolean active;

	@ManyToMany(targetEntity = Permission.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "group_permissions", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> grouppermissions  = new HashSet<>();

	public Group_() {
		// TODO Auto-generated constructor stub
	}

	public Group_(int id, @NotNull String groupname, @NotNull String description, boolean active,
			Set<Permission> grouppermissions) {
		this.id = id;
		this.groupname = groupname;
		this.description = description;
		this.active = active;
		this.grouppermissions = grouppermissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<Permission> getGrouppermissions() {
		return grouppermissions;
	}

	public void setGrouppermissions(Set<Permission> grouppermissions) {
		this.grouppermissions = grouppermissions;
	}

	@Override
	public String toString() {
		return "Group_ [id=" + id + ", groupname=" + groupname + ", description=" + description + ", active=" + active
				+ ", grouppermissions=" + grouppermissions + "]";
	}

	

}
