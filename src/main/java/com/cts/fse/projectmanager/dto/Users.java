package com.cts.fse.projectmanager.dto;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "emp_id")
	private String empId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_project", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Project> project = new HashSet<>();
	 
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	Set<Task> task = new HashSet<Task>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Set<Project> getProject() {
		return project;
	}

	public void setProject(Set<Project> project) {
		this.project = project;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
	}
}
