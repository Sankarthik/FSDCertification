package com.cts.casestudy.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Digits(integer = 9, fraction = 0)
	private Integer employeeId;
	
	@Size(max = 100)
	private String firstName;
	
	@Size(max = 100)
	private String lastName;
	
	/*
	 * One to Many should be handled here in a real time thinking as it increases lot of work,
	 * To simplify using One To One mapping fo now
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	
	/*
	 * One to Many should be handled here in a real time thinking as it increases lot of work,
	 * To simplify using One To One mapping fo now
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TASK_ID")
	private Task task;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User(@Digits(integer = 9, fraction = 0) Integer employeeId, @Size(max = 100) String firstName,
			@Size(max = 100) String lastName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
		super();
	}
}
