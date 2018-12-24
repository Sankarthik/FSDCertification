package com.cts.casestudy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.casestudy.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
