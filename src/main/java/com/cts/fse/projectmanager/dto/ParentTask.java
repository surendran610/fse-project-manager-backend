package com.cts.fse.projectmanager.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "parent_task")
	private String parentTask;

	@OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
	Set<Task> task = new HashSet<Task>();

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}

}
