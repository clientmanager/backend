package com.clientmanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Team")
public class Team {
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

	@ManyToMany(targetEntity = Permission.class, cascade = { CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinTable(name = "teampermissions", joinColumns = @JoinColumn(name = "teamid"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> grouppermissions;

	public Team() {
		grouppermissions = new HashSet<>();
		// TODO Auto-generated constructor stub
	}

	public Team(int id, @NotNull String groupname, @NotNull String description, boolean active,
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
		return "Team [id=" + id + ", groupname=" + groupname + ", description=" + description + ", active=" + active
				+ ", grouppermissions=" + grouppermissions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((groupname == null) ? 0 : groupname.hashCode());
		result = prime * result + ((grouppermissions == null) ? 0 : grouppermissions.hashCode());
		result = prime * result + id;
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
		Team other = (Team) obj;
		if (active != other.active)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groupname == null) {
			if (other.groupname != null)
				return false;
		} else if (!groupname.equals(other.groupname))
			return false;
		if (grouppermissions == null) {
			if (other.grouppermissions != null)
				return false;
		} else {
			if (!grouppermissions.containsAll(other.grouppermissions)
					|| !other.grouppermissions.containsAll(grouppermissions)) {
				return false;
			}
//			if(grouppermissions.size() != other.grouppermissions.size()) {
//				return false;
//			}
//			for(Permission permission : grouppermissions) {
//				boolean match = false;
//				for(Permission permission2 : other.grouppermissions) {
//					if(permission.equals(permission2)) {
//						match = true;
//					}
//				}
//				if(match) {
//					return false;
//				}
//				
//			}
		}
		if (id != other.id)
			return false;
		return true;
	}

	public boolean validate() {
		System.out.println("team validate - 1");
		boolean ret = true;
		System.out.println("team validate - 2");
		if (id < 0) {
			ret = false;
		}
		System.out.println("team validate - 3");
		if (groupname == null || groupname.equals("")) {
			ret = false;
		}
		System.out.println("team validate - 4");
		if (description == null || description.equals("")) {
			ret = false;
		}
		System.out.println("team validate - 5");
		if (grouppermissions == null) {
			grouppermissions = new HashSet<>();
		}else {
			System.out.println("team validate - 6");
			Iterator<Permission> it = grouppermissions.iterator();
			System.out.println("team validate - 6.2");
			while(it.hasNext()) {
				System.out.println("team validate - 6.5");
				if(!it.next().validate()) {
					ret = false;
				}
				System.out.println("team validate - 6.7");
			}
			System.out.println("team validate - 7");
		}
		System.out.println("team validate - 8");
		return ret;
	}

}
