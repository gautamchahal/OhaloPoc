package com.ohalo.assignment.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohalo.assignment.model.TVShow;
import com.ohalo.assignment.model.User;
import com.ohalo.assignment.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ShowService showService;
	
	
	
	//getting all records from TVShows
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(user->users.add(user));
		return users;
	}

	public User getUserById(String id) {
		return userRepo.findById(id).get();
	}

	public void saveOrUpdate(User user) {
		userRepo.save(user);
	}
	
	public List<TVShow> getSelectedShowsForSeason(String userId, String title, int season){
		Optional<User> user = userRepo.findById(userId);
		List<TVShow> likedShowsForSeason = new ArrayList<>();
		if(user.isPresent()) {
			likedShowsForSeason = user.get().getLikedShows().parallelStream().filter(show->show.getTitle().equalsIgnoreCase(title)).filter(show->show.getSeason()==season).collect(Collectors.toList());
			
		}
		return likedShowsForSeason;
	}
	
	public void updateSelectedShowsForSeason(String userId, String title, int season, List<Integer> showIds) {
		Optional<User> user = userRepo.findById(userId);
		Set<TVShow> likedShowsForSeason = new HashSet<>();
	    likedShowsForSeason.addAll(showService.getShowByIds(showIds));
		if(user.isPresent()) {
			Set<TVShow> likedShows = user.get().getLikedShows();
			if(likedShows.isEmpty()) {
				likedShows.addAll(likedShowsForSeason);
			}else {
					Set<TVShow> oldLikedShowsForSeason = likedShows.parallelStream().filter(show->show.getTitle().equalsIgnoreCase(title) && show.getSeason()==season).collect(Collectors.toSet());
				    likedShows.removeAll(oldLikedShowsForSeason);
				    likedShows.addAll(likedShowsForSeason);
			}
			userRepo.save(user.get());
		}else {
			User newUser = new User();
			newUser.setId(userId);
			newUser.setLikedShows(likedShowsForSeason);
			userRepo.save(newUser);
		}
	}

	//deleting a specific record  
	public void delete(String id)   
	{  
		userRepo.deleteById(id);  
	}  

}
