package com.clientmanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@Column(name = "password")
	@NotNull
	private String password;

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

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fname=" + fname + ", mname="
				+ mname + ", lname=" + lname + ", gender=" + gender + ", jobs=" + jobs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + gender;
		result = prime * result + id;
		result = prime * result + ((jobs == null) ? 0 : jobs.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (fname == null) {
			if (other.fname != null) {
				return false;
			}
		} else if (!fname.equals(other.fname)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (jobs == null) {
			if (other.jobs != null) {
				return false;
			}
		} else if (!jobs.equals(other.jobs)) {
			if (jobs.size() != other.jobs.size()) {
				return false;
			}
			if (!jobs.containsAll(other.jobs)
					|| !other.jobs.containsAll(jobs)) {
				return false;
			}
		}
		if (lname == null) {
			if (other.lname != null) {
				return false;
			}
		} else if (!lname.equals(other.lname)) {
			return false;
		}
		if (mname == null) {
			if (other.mname != null) {
				return false;
			}
		} else if (!mname.equals(other.mname)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	public boolean validate() {
		boolean ret = true;
		if (id < 0) {
			ret = false;
		}
		if (username == null || username.equals("")) {
			ret = false;
		}
		if (password == null || password.equals("")) {
			ret = false;
		}
		if (fname == null || fname.equals("")) {
			ret = false;
		}
		if (lname == null || lname.equals("")) {
			ret = false;
		}
		if (gender != 'M' && gender != 'F') {
			ret = false;
		}
		if (jobs == null) {
			jobs = new ArrayList<>();
		} else {
			for (int i = 0; i < jobs.size(); i++) {
				if (!jobs.get(i).validate()) {
					ret = false;
				}
			}
		}
		return ret;
	}

}
