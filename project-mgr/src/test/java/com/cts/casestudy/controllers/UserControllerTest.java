package com.cts.casestudy.controllers;

import static com.cts.casestudy.util.TestUtility.APPLICATION_JSON_UTF8;
import static com.cts.casestudy.util.TestUtility.convertObjectToJsonBytes;
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

import com.cts.casestudy.entities.User;
import com.cts.casestudy.service.UserService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;

	@Test
	public void contextLoads() {	}
	
	@Test
	public void findAllUsers() throws Exception {
		User user = new User(1, "Sankar", "Giridharan");
		
		when(service.findAllUsers()).thenReturn(asList(user));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
		
		verify(service, times(1)).findAllUsers();
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findUser() throws Exception {
		User user = new User(1, "Sankar", "Giridharan");
		
		when(service.findUser(1)).thenReturn(user);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(service, times(1)).findUser(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findUserByProjectId() throws Exception {
		User user = new User(1, "Sankar", "Giridharan");
		
		when(service.findUserByProject(1)).thenReturn(user);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/projects/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(service, times(1)).findUserByProject(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void findUserByTaskId() throws Exception {
		User user = new User(1, "Sankar", "Giridharan");
		
		when(service.findUserByTask(1)).thenReturn(user);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/tasks/1")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(service, times(1)).findUserByTask(1);
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void addUser() throws Exception {
		User user = new User(1, "Sankar", "Giridharan");

		Mockito.doNothing().when(service).addUser(user);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		ArgumentCaptor<User> userCapture = ArgumentCaptor.forClass(User.class);
		verify(service, times(1)).addUser(userCapture.capture());
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void updateUser() throws Exception {
		User user = new User(1, "Sankar", "Giridharan");

		Mockito.doNothing().when(service).updateUser(user);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/users")
				.contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(user))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		ArgumentCaptor<User> userCapture = ArgumentCaptor.forClass(User.class);
		verify(service, times(1)).updateUser(userCapture.capture());
        verifyNoMoreInteractions(service);
	}
	
	@Test
	public void deleteUser() throws Exception {
		Mockito.doNothing().when(service).deleteUser(1);
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/1")
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk());	
		verify(service, times(1)).deleteUser(1);
        verifyNoMoreInteractions(service);
	}
}
