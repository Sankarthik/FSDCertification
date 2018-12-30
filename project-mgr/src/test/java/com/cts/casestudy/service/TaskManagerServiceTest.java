package com.cts.casestudy.service;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.casestudy.entities.Task;
import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerServiceTest { 
	
	@Autowired
    private TaskManagerService taskService;
	@MockBean
	private UserRepository userRepo;
	@Autowired
	private UserService service;
	
	@Test
    public void findAllTasks() {
		addTask();
    	List<Task> tasks = taskService.findAllTasks();
    	assertNotNull(tasks);
    	assertThat(tasks, hasSize(1));
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
    public void completeTask() {
    	final Task task = taskService.findTask(1);
    	task.setEndDate(new Date());
    	task.setStatus("COMPLETED");
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
    
    @Test
    public void endTask() {
    	taskService.endTask(1);
    }
    
    @Test
    public void findUserByTask() {
		User user = new User(1, "Sankar", "Giridharan");

		when(userRepo.findByTaskId(1)).thenReturn(asList(user));
		assertNotNull(service.findUserByTask(1));
	}
}
