package com.cts.casestudy.service;

import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.casestudy.entities.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectServiceTest { 
	
	@Autowired
    private ProjectService service;

    @Test
    public void findAllProjects() {
    	addProject();
    	List<Project> projects = service.findAllProjects();
    	assertNotNull(projects);
    	assertThat(projects, hasSize(1));
    }
    
    @Test
    public void findAllProjectsWithTask() {
    	addProject();
    	List<Project> projects = service.findAllProjectsWithTask();
    	assertNotNull(projects);
    	assertThat(projects, hasSize(1));
    }

    @Test
    public void findById() {
    	assertNotNull(service.findProject(1));
    }    

    @Test
    public void addProject() {
    	Project project = new Project(1, "Project 1", 
	             valueOf(now()), valueOf(now().plusDays(10)), 
	             5);
    	
        service.addProject(project);
    }
    
    @Test
    public void updateProject() {
    	addProject();
    	final Project project = service.findProject(1);
    	project.setEndDate(new Date());
    	project.setPriority(25);
    	
    	service.updateProject(project);
    	assertThat(service.findProject(1).getPriority(), is(25));
    }
    
    @Test
    public void endProject() {
    	service.endProject(1);
    }
}
