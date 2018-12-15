package com.cts.casestudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.casestudy.controllers.TaskManagerController;
import com.cts.casestudy.repos.TaskManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
//@WebMvcTest(TaskManagerController.class)
public class CtsTaskMgrApplicationTest {
	
	/*@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskManagerRepository repo;*/

	@Test
	public void contextLoads() {
		/*Task dummyTask = new Task(1, "Task 1", new Date(), new Date("31-12-2018"), 5);
		
		List<Object> tasks = Arrays.asList(dummyTask);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/tasks")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));*/

		
	}

}
