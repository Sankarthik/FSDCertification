package com.cts.casestudy.service;

import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.casestudy.entities.ParentTask;
import com.cts.casestudy.entities.Project;
import com.cts.casestudy.entities.Task;
import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.ParentTaskManagerRepository;
import com.cts.casestudy.repos.ProjectRepository;
import com.cts.casestudy.repos.TaskManagerRepository;
import com.cts.casestudy.repos.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnHappyScenarioServicesTest { 
	
	@Autowired
    private TaskManagerService taskService;
	@Autowired
	private UserService userService;
	@Autowired
    private ProjectService projService;
	
	@MockBean
	private TaskManagerRepository taskRepo;
	@MockBean
	private ParentTaskManagerRepository parentRepo;
	@MockBean
	private ProjectRepository projRepo;
	@MockBean
	private UserRepository userRepo;
	
	@Test
    public void findNonExistTask() {
		when(taskRepo.findById(1)).thenReturn(Optional.empty());
    	assertNull(taskService.findTask(1));
    }
	
	@Test
    public void deleteNonExistTask() {
		when(taskRepo.findById(1)).thenReturn(Optional.empty());
    	taskService.deleteTask(1);
    }
	
	@Test
    public void endExistingTask() {
		final Task task = new Task();
        task.setTask("Test Task");
        task.setStartDate(new Date());
		when(taskRepo.findById(1)).thenReturn(Optional.of(task));
		
    	taskService.endTask(1);
    }
	
	@Test
    public void findTaskByProject() {
		final Task task = new Task();
        task.setTask("Test Task");
        task.setStartDate(new Date());
		when(taskRepo.findByProjectId(1)).thenReturn(asList(task));
    	List<Task> tasks = taskService.findTaskByProject(1);
    	assertNotNull(tasks);
    	assertThat(tasks, hasSize(1));
    }
	
	@Test
    public void addTaskWithParentAndProjectAndUser() {
		Task task = new Task(1, "Test Task", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5, new ParentTask(1, "Parent Task"));
		task.setProjId(3);
		task.setUserId(3010);
		
		Project project = new Project(3, "Project 3", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5);
		
		User user = new User(3010, "Sankar", "Giridharan");
		
		when(taskRepo.findById(task.getParentTask().getId())).thenReturn(Optional.of(task));
		when(parentRepo.findById(task.getParentTask().getId())).thenReturn(Optional.empty());
		when(projRepo.findById(3)).thenReturn(Optional.of(project));
		when(userRepo.findById(3010)).thenReturn(Optional.of(user));
		
		taskService.addTask(task);
    }
	
	@Test(expected = RuntimeException.class)
    public void addTaskWithParentWhichIsNotPresent() {
		Task task = new Task(1, "Test Task", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5, new ParentTask(1, "Parent Task"));
		
		when(taskRepo.findById(task.getParentTask().getId())).thenReturn(Optional.empty());
		taskService.addTask(task);
    }
	
	@Test
    public void findNonExistUser() {
		when(userRepo.findById(1)).thenReturn(Optional.empty());
    	assertNull(userService.findUser(1));
    }
	
	@Test
    public void deleteNonExistUser() {
		when(userRepo.findById(1)).thenReturn(Optional.empty());
		userService.deleteUser(1);
    }
	
	@Test(expected = RuntimeException.class)
    public void addUserWhichAlreadyExist() {
		User user = new User(3010, "Sankar", "Giridharan");

		when(userRepo.findById(3010)).thenReturn(Optional.of(user));
		userService.addUser(user);
    }
	
	@Test
    public void findNonExistProject() {
		when(projRepo.findById(1)).thenReturn(Optional.empty());
    	assertNull(projService.findProject(1));
    }
	
	@Test
    public void endNonExistProject() {
		when(projRepo.findById(1)).thenReturn(Optional.empty());
		projService.endProject(1);
    }
	
	@Test
    public void addProjectwithoutData() {
    	Project project = null;
    	projService.addProject(project);
    }
    
	@Test
    public void addProjectWithNonExistingUser() {
		Project project = new Project(1, "Project 1", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5);
		project.setManagerId(30);
		when(userRepo.findById(project.getManagerId())).thenReturn(Optional.empty());
    	projService.addProject(project);
    }
}
