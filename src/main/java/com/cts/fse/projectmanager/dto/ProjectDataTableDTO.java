package com.cts.fse.projectmanager.dto;

public class ProjectDataTableDTO {

	private long projectId;
	private String projectName;
	private long taskCount;
	private long taskCompletedCount;
	private String startDate;
	private String endDate;
	private int priority;
	private String firstNameUser;
	private long userId;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(long taskCount) {
		this.taskCount = taskCount;
	}

	public long getTaskCompletedCount() {
		return taskCompletedCount;
	}

	public void setTaskCompletedCount(long taskCompletedCount) {
		this.taskCompletedCount = taskCompletedCount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

}
