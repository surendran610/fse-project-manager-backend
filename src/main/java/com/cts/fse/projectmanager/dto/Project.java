package com.cts.fse.projectmanager.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project")
	private String project;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "priority")
	private int priority;
	
	@ManyToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Users> users = new HashSet<>();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	Set<Task> task = new HashSet<Task>();

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}

	/*public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}*/

	/*public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}*/

}
