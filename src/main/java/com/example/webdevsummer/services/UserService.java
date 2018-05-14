package com.example.webdevsummer.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer.repositories.UserRepository;
import com.example.webdevsummer.model.*;

@RestController
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) {
		List<User> u = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		//System.out.println(u.size());
		if(u.isEmpty())
			return null;
		else return u.get(0);
	}

	
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser) {
		List<User> data = (List<User>) repository.findUserByUsername(newUser.getUsername());
		User user=data.get(0);
		user.setPhone(newUser.getPhone());
		user.setEmail(newUser.getEmail());
		user.setRole(newUser.getRole());
		user.setDateOfBirth(newUser.getDateOfBirth());
		repository.save(user);
		return newUser;
	}
	
	
	//@GetMapping("/api/register/{userName}")
	public List<User> findUserByUsername(String username) {
		return  (List<User>) repository.findUserByUsername(username);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user) {
		List users = findUserByUsername(user.getUsername());
		if(!users.isEmpty())
			return null;
		else
			return createUser(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		 repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	
	@GetMapping("/api/user")
	public List<User> findAllUsers(@RequestParam(name="username", required=false) String username) {
			if(username != null) {
				return (List<User>) repository.findUserByUsername(username);
			}
			return (List<User>) repository.findAll();

	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent())
			return user.get();
		return null;
	}
	
}
