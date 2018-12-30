package com.cts.casestudy.controllers;

import static com.cts.casestudy.util.TestUtility.APPLICATION_JSON_UTF8;
import static com.cts.casestudy.util.TestUtility.convertObjectToJsonBytes;
import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cts.casestudy.entities.ParentTask;
import com.cts.casestudy.entities.Task;
import com.cts.casestudy.service.TaskManagerService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(TaskManagerController.class)
public class TaskManagerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskManagerService service;

	@Test
	public void contextLoads() {	}
	
	@Test
	public void findAllTasks() throws Exception {
		Task task = new Task(1, "Test Task", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5, null);
		
		when(service.findAllTasks()).thenReturn(asList(task));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
		
		verify(service, times(1)).findAllTasks();
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findTaskById() throws Exception {
		Task task = new Task(1, "Test Task", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5, null);
		
		when(service.findTask(1)).thenReturn(task);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/tasks/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(service, times(1)).findTask(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findTaskByProject() throws Exception {
		Task task = new Task(1, "Test Task", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5, new ParentTask(1, "Parent Task"));
		
		when(service.findTaskByProject(1)).thenReturn(asList(task));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/tasks/projects/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(service, times(1)).findTaskByProject(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void addTask() throws Exception {
		Task task = new Task(1, "Test Task", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5, null);
		Mockito.doNothing().when(service).addTask(task);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(task))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		ArgumentCaptor<Task> taskCapture = ArgumentCaptor.forClass(Task.class);
		verify(service, times(1)).addTask(taskCapture.capture());
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void updateTask() throws Exception {
		Task task = new Task(1, "Test Task", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5, null);
		Mockito.doNothing().when(service).updateTask(task);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/tasks")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(task))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		ArgumentCaptor<Task> taskCapture = ArgumentCaptor.forClass(Task.class);
		verify(service, times(1)).updateTask(taskCapture.capture());
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void deleteTask() throws Exception {
		Mockito.doNothing().when(service).deleteTask(1);
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1")
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		verify(service, times(1)).deleteTask(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void endTask() throws Exception {
		Mockito.doNothing().when(service).endTask(1);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/tasks/1")
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		verify(service, times(1)).endTask(1);
        verifyNoMoreInteractions(service);
	}
	
	
}
