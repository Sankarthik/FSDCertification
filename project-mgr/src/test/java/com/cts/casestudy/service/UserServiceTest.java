package com.cts.casestudy.service;

import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.casestudy.entities.Project;
import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.TaskManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	@Autowired
	private UserService service;	
	/*@Autowired
	private TaskManagerService taskService;*/
	@Autowired
	private ProjectService projService;
	@Autowired
	TaskManagerRepository taskRepo;
	/*@Mock
	private UserRepository userRepo;*/

	@Test
	public void findAllUsers() {
		addUser();
		List<User> users = service.findAllUsers();
		assertNotNull(users);
		assertThat(users, hasSize(1));
	}

	@Test
	public void findUser() {
		assertNotNull(service.findUser(1));
	}

	@Test
	public void findUserByProject() {
		// addUser();
		addProject();
		assertNotNull(service.findUserByProject(1));
	}

	/*@Test
	public void findUserByTask() {
		if(service.findUser(1) == null) {
			addUser();
		}
		addTask();
		final Optional<Task> optTask = taskRepo.findByTask("User Task").stream().findFirst();
		if(optTask.isPresent()) {
			Task task = optTask.get();

			assertNotNull(service.findUserByTask(task.getId()));
		}
		User user = new User(1, "Sankar", "Giridharan");

		when(userRepo.findByTaskId(1)).thenReturn(asList(user));
		assertNotNull(service.findUserByTask(1));
	}*/

	@Test
	public void updateUser() {
		// addUser();
		final User user = service.findUser(1);
		user.setFirstName("Karthik");
		user.setLastName("IIHT");

		service.updateUser(user);
		final User postUpdate = service.findUser(1);

		assertThat(postUpdate.getFirstName(), is(user.getFirstName()));
		assertThat(postUpdate.getLastName(), is(user.getLastName()));
	}

	@Test
	public void addUser() {
		final User user = new User(1, "Sankar", "Giridharan");
		service.addUser(user);
	}

	@Test
	public void deleteUser() {
		service.deleteUser(1);
	}

	private void addProject() {
		Project project = new Project(1, "Project 1", valueOf(now()), valueOf(now().plusDays(10)), 5);
		project.setManagerId(1);

		projService.addProject(project);
	}
}
