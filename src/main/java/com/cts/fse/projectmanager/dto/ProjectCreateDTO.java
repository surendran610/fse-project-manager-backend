package com.cts.fse.projectmanager.dto;

import java.util.Date;

public class ProjectCreateDTO {

	private String project;

	private Date startDate;

	private Date endDate;

	private int priority;

	private long userId;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
