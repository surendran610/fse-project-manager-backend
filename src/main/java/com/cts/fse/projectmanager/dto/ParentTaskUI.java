package com.cts.fse.projectmanager.dto;

public class ParentTaskUI {
	
	private long id;
	
	private String parentTaskName;

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
