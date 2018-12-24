package com.cts.casestudy.service;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.casestudy.entities.User;
import com.cts.casestudy.repos.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	public User findUser(Integer employeeId) {
		Optional<User> user = userRepo.findById(employeeId);
		return user.isPresent() ? user.get() : null;
	}

	public void addUser(User user) {
		if (user != null) {
			Optional<User> optUser = userRepo.findById(user.getEmployeeId());
			//ofNullable(optUser).orElseThrow(() -> new RuntimeException("Employee Id already exists"));
			if(optUser.isPresent()) {
				throw new RuntimeException("Employee Id already exists");
			}
			userRepo.save(user);
		}
	}

	public void updateUser(User user) {
		userRepo.save(user);
	}

	public void deleteUser(Integer employeeId) {
		Optional<User> optUser = userRepo.findById(employeeId);
		if (optUser.isPresent()) {
			userRepo.deleteById(employeeId);
		}
	}
}