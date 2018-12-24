package com.cts.casestudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.casestudy.entities.User;
import com.cts.casestudy.service.UserService;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(path="/users", method=RequestMethod.GET)
	public List<User> findAllUsers(){
		return service.findAllUsers();
	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.GET)
	public User findUser(@PathVariable Integer id){
		return service.findUser(id);
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		 service.addUser(user);
	}
	
	@RequestMapping(path="/users", method=RequestMethod.PUT)
	public void updateUser(@RequestBody User user){
		 service.updateUser(user);
	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id){
		 service.deleteUser(id);
	}
}
