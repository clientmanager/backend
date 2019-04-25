package com.clientmanager.model;

import java.util.HashMap;
import java.util.Map;

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
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "username", unique = true)
	@NotNull
	private String username;

	@Column(name = "fname")
	@NotNull
	private String fname;

	@Column(name = "mname")
	private String mname;

	@Column(name = "lname")
	@NotNull
	private String lname;

	@Column(name = "gender")
	@NotNull
	private char gender;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "_userToGroup_AndRole", joinColumns = { @JoinColumn(name = "user") }, inverseJoinColumns = {
			@JoinColumn(name = "role") })
	@MapKeyJoinColumn(name = "group")
	private Map<Group_, Role> _userToGroup_AndRoleMappers = new HashMap<>();

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, @NotNull String username, @NotNull String fname, String mname, @NotNull String lname,
			@NotNull char gender, Map<Group_, Role> _userToGroup_AndRoleMappers) {
		this.id = id;
		this.username = username;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.gender = gender;
		this._userToGroup_AndRoleMappers = _userToGroup_AndRoleMappers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Map<Group_, Role> get_userToGroup_AndRoleMappers() {
		return _userToGroup_AndRoleMappers;
	}

	public void set_userToGroup_AndRoleMappers(Map<Group_, Role> _userToGroup_AndRoleMappers) {
		this._userToGroup_AndRoleMappers = _userToGroup_AndRoleMappers;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", mname=" + mname + ", lname="
				+ lname + ", gender=" + gender + ", _userToGroup_AndRoleMappers=" + _userToGroup_AndRoleMappers + "]";
	}

}
