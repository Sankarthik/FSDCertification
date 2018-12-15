package com.cts.casestudy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class ParentTask {

	@Id
	private Integer id;
	@Size(max = 100)
	private String task;

	public void setId(Integer id) {
		this.id = id;
	}	

	public Integer getId() {
		return id;
	}	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public ParentTask() {
		super();
	}
	
	public ParentTask(Integer id, String task) {
		super();
		this.id = id;
		this.task = task;
	}
}
