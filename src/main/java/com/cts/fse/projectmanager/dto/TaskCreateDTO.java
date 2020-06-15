package com.cts.fse.projectmanager.dto;

import java.sql.Date;

public class TaskCreateDTO {
	
	private long id;

	private String task;

	private Date startDate;

	private Date endDate;

	private String status;

	private long parentId;

	private long projectId;

	private long userId;
	
	private String isParentTask;

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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getIsParentTask() {
		return isParentTask;
	}

	public void setIsParentTask(String isParentTask) {
		this.isParentTask = isParentTask;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
