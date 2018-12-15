package com.cts.casestudy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.casestudy.entities.ParentTask;

@Repository
public interface ParentTaskManagerRepository extends JpaRepository<ParentTask, Integer>{

}
