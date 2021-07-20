package com.ohalo.assignment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ohalo.assignment.model.TVShow;
import com.ohalo.assignment.service.ShowService;

@RestController
public class TVShowController {
	
	@Autowired
	ShowService showService;
	
	@GetMapping("/allshow")
	private List<TVShow> getAllShows(){
		return showService.getAllShows();
	}
	
	@GetMapping("/show")
	private List<TVShow> getShowWithTitleAndSeason(@RequestParam String title,@RequestParam int season) {
		return showService.getShowByTitleAndSeason(title, season);
	}
	
	@GetMapping("/show/{id}")
	private TVShow getShow(@PathVariable("id") int id) {
		return showService.getShowById(id);
	}
	
	@PostMapping("/show")
	private int saveShow(@RequestBody TVShow tvShow) {
		showService.saveOrUpdate(tvShow);
		return tvShow.getId();
	}
	
	@DeleteMapping("/show/{id}")
	private void delShow(@PathVariable("id") int id) {
		showService.delete(id);
	}

}
