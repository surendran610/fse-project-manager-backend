package com.cts.fse.projectmanager.dto;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "task")
	private String task;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "status")
	private String status;
	
	@Column(name = "priority")
	private int priority;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Project project;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "parent_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	ParentTask parentTask;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	Users users;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ParentTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
