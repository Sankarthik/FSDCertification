package com.cts.casestudy.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.casestudy.entities.ParentTask;
import com.cts.casestudy.entities.Project;
import com.cts.casestudy.entities.Task;
import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.ParentTaskManagerRepository;
import com.cts.casestudy.repos.ProjectRepository;
import com.cts.casestudy.repos.TaskManagerRepository;
import com.cts.casestudy.repos.UserRepository;

@Service
public class TaskManagerService {

	@Autowired
	TaskManagerRepository repo;

	@Autowired
	ParentTaskManagerRepository parentRepo;

	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	UserRepository userRepo;

	public List<Task> findAllTasks() {
		return repo.findAll();
	}

	public Task findTask(Integer id) {
		Optional<Task> task = repo.findById(id);
		return task.isPresent() ? task.get() : null;
	}

	public void addTask(Task task) {
		setParentTask(task);
		setProject(task);

		repo.save(task);
		
		setUser(task);
	}

	public void updateTask(Task task) {
		setUser(task);
		repo.save(task);
	}

	public void deleteTask(Integer id) {
		Optional<Task> taskOpt = repo.findById(id);
		if (taskOpt.isPresent()) {
			Task task = taskOpt.get();
			task.setParentTask(null);
			task.setProject(null);
			repo.deleteById(id);
		}
	}

	public void endTask(Integer id) {
		Optional<Task> taskOpt = repo.findById(id);
		if (taskOpt.isPresent()) {
			Task task = taskOpt.get();
			task.setEndDate(new Date());
			repo.save(task);
		}
	}
	
	public List<Task> findTaskByProject(Integer projectId) {
		return repo.findByProjectId(projectId);
	}

	private void setParentTask(Task task) {
		if (task.getParentTask() != null) {
			Optional<Task> optTask = repo.findById(task.getParentTask().getId());

			if (!optTask.isPresent()) {
				throw new RuntimeException("No Task id is created");
			}

			Optional<ParentTask> pt = parentRepo.findById(task.getParentTask().getId());

			if (pt.isPresent()) {
				task.setParentTask(pt.get());
			} else {
				ParentTask parentTask = new ParentTask(task.getParentTask().getId(), optTask.get().getTask());
				task.setParentTask(parentTask);
			}
		}
	}

	private void setUser(Task task) {
		if (task.getUserId() != null) {
			Optional<User> optUser = userRepo.findById(task.getUserId());
			if (optUser.isPresent()) {
				User user = optUser.get();
				user.setTask(task);
				userRepo.save(user);
			}
		}
	}

	private void setProject(Task task) {
		if (task.getProjId() != null) {
			Optional<Project> optProject = projectRepo.findById(task.getProjId());
			if (optProject.isPresent()) {
				Project project = optProject.get();
				task.setProject(project);
			}
		}
	}
}
