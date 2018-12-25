package com.cts.casestudy.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.casestudy.entities.Project;
import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.ProjectRepository;
import com.cts.casestudy.repos.UserRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	UserRepository userRepo;

	public List<Project> findAllProjects() {
		return projectRepo.findAll();
	}

	public Project findProject(Integer projectId) {
		Optional<Project> project = projectRepo.findById(projectId);
		return project.isPresent() ? project.get() : null;
	}

	public void addProject(Project project) {
		if (project != null) {
			projectRepo.save(project);
			
			Optional<User> optUser = userRepo.findById(project.getManagerId());
			if(optUser.isPresent()) {
				User user = optUser.get();
				user.setProject(project);
				userRepo.save(user);
			}
		}
	}

	public void updateProject(Project project) {
		addProject(project);
	}

	public void endProject(Integer id) {
		Optional<Project> optProject = projectRepo.findById(id);
		if (optProject.isPresent()) {
			Project project = optProject.get();
			project.setEndDate(new Date());
			projectRepo.save(project);
		}
	}
}
