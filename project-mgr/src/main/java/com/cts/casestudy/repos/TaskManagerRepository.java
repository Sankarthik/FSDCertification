package com.cts.casestudy.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.casestudy.entities.Task;

@Repository
public interface TaskManagerRepository extends JpaRepository<Task, Integer>{
	
	//Below implementation for filters in backend in case needed
	public List<Task> findByTask(String task);
	public List<Task> findByStartDate(Date startDate);
	public List<Task> findByEndDate(Date endDate);
	public List<Task> findByPriorityBetween(Integer from, Integer to);
	public List<Task> findByPriorityGreaterThanEqual(Integer priority);
	public List<Task> findByPriorityLessThanEqual(Integer priority);
	public List<Task> findByParentTaskTask(String name);
	public List<Task> findByParentTaskId(Integer id);
}
