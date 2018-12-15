package com.cts.casestudy.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.casestudy.entities.Task;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerServiceTest { 
	
	@Autowired
    private TaskManagerService taskService;

    @Test
    public void findAllTasks() {
    	addTask();
    	assertNotNull(taskService.findAllTasks());
    }

    @Test
    public void findById() {
    	assertNotNull(taskService.findTask(1));
    }

    @Test
    public void updateTask() {
    	final Task task = taskService.findTask(1);
    	task.setEndDate(new Date());
    	taskService.updateTask(task);
    }

    @Test
    public void addTask() {
        final Task task = new Task();
        task.setTask("Test Task");
        task.setStartDate(new Date());
        taskService.addTask(task);
    }

    @Test
    public void deleteTask() {
    	taskService.deleteTask(1);
    }
}
