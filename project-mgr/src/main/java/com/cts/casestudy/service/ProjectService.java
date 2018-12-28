package com.cts.casestudy.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.casestudy.entities.Project;
import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.ProjectRepository;
import com.cts.casestudy.repos.UserRepository;

@Service
public class ProjectService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	UserRepository userRepo;

	public List<Project> findAllProjects() {
		return projectRepo.findAll();
	}
	
	public List<Project> findAllProjectsWithTask() {
		 List<Project> projects = new ArrayList<>();
		 projectRepo.findAll().stream().forEach(p -> {
			 Project project = new Project(p.getId(), p.getProject(), 
					 					   p.getStartDate(), p.getEndDate(), 
					 					   p.getPriority());
			 project.setCountOfTasks(fetchTaskCount(p.getId()).intValue());
			 
			 projects.add(project);
		 });
		 
		 return projects;
	}

	public Project findProject(Integer projectId) {
		Optional<Project> project = projectRepo.findById(projectId);
		System.out.println(fetchTaskCount(projectId));
		return project.isPresent() ? project.get() : null;
	}

	public void addProject(Project project) {
		if (project != null) {
			projectRepo.save(project);
			
			if(project.getManagerId() != null) {
				Optional<User> optUser = userRepo.findById(project.getManagerId());
				if(optUser.isPresent()) {
					User user = optUser.get();
					user.setProject(project);
					userRepo.save(user);
				}
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

	public BigInteger fetchTaskCount(int projectId) {
	    Query nativeQuery = entityManager.createNativeQuery("select count(*) from task t "
	    		+ "left outer join project p on t.project_id = p.id "
	    		+ "where p.id=:id")
	    		.setParameter("id", projectId);
	    
	    return (BigInteger) nativeQuery.getSingleResult();
	}
}
