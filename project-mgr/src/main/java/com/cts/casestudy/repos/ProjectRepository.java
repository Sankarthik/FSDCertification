package com.cts.casestudy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.casestudy.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
