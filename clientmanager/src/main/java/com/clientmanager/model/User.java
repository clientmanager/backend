package com.clientmanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "jobs", joinColumns = { @JoinColumn(name = "user") }, inverseJoinColumns = {
//			@JoinColumn(name = "role") })
//	@MapKeyJoinColumn(name = "group_")
//	private Map<Group_, Role> jobs = new HashMap<>();

	@OneToMany
	private List<Job> jobs;

	public User() {
		jobs = new ArrayList<>();
		// TODO Auto-generated constructor stub
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

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fname=" + fname + ", mname=" + mname + ", lname="
				+ lname + ", gender=" + gender + ", jobs=" + jobs + "]";
	}
	
	

}
