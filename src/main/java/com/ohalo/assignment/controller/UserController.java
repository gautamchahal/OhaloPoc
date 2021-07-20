package com.ohalo.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ohalo.assignment.model.TVShow;
import com.ohalo.assignment.model.User;
import com.ohalo.assignment.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	private List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	private User getUser(@PathVariable("id") String id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/user")
	private String saveUser(@RequestBody User user) {
		userService.saveOrUpdate(user);
		return user.getId();
	}
	
	@GetMapping("/user/select")
	private List<TVShow> getSelectedShowsForSeason(@RequestParam String id,@RequestParam String title,@RequestParam int season) {
		return userService.getSelectedShowsForSeason(id, title, season);
	}
	
	@PutMapping("/user/select")
	private void updateSelectedShowsForSeason(@RequestParam String id,@RequestParam String title,@RequestParam int season,@RequestBody List<Integer> showIds) {
		userService.updateSelectedShowsForSeason(id, title,season,showIds);
	}
	
	@DeleteMapping("/user/{id}")
	private void delShow(@PathVariable("id") String id) {
		userService.delete(id);
	}

}
