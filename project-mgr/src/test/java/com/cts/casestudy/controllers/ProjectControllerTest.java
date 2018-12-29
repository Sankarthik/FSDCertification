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

import com.cts.casestudy.entities.Project;
import com.cts.casestudy.service.ProjectService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProjectService service;

	@Test
	public void contextLoads() {	}
	
	@Test
	public void findAllProjects() throws Exception {
		Project project = new Project(1, "Project 1", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5);
		
		when(service.findAllProjects()).thenReturn(asList(project));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/projects")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
		
		verify(service, times(1)).findAllProjects();
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findAllProjectsWithTask() throws Exception {
		Project project = new Project(1, "Project 1", 
				             valueOf(now()), valueOf(now().plusDays(10)), 
				             5);
		
		when(service.findAllProjectsWithTask()).thenReturn(asList(project));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/projects/tasks")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
		
		verify(service, times(1)).findAllProjectsWithTask();
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findProject() throws Exception {
		Project project = new Project(1, "Project 1", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5);
		
		when(service.findProject(1)).thenReturn(project);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/projects/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(service, times(1)).findProject(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void addProject() throws Exception {
		Project project = new Project(1, "Project 1", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5);
		
		Mockito.doNothing().when(service).addProject(project);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/projects")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(project))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		ArgumentCaptor<Project> projCapture = ArgumentCaptor.forClass(Project.class);
		verify(service, times(1)).addProject(projCapture.capture());
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void updateProject() throws Exception {
		Project project = new Project(1, "Project 1", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5);
		Mockito.doNothing().when(service).updateProject(project);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/projects")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(project))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		ArgumentCaptor<Project> projCapture = ArgumentCaptor.forClass(Project.class);
		verify(service, times(1)).updateProject(projCapture.capture());
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void endProject() throws Exception {
		Mockito.doNothing().when(service).endProject(1);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/projects/1")
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		verify(service, times(1)).endProject(1);
        verifyNoMoreInteractions(service);
	}
}
