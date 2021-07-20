package com.ohalo.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohalo.assignment.model.TVShow;
import com.ohalo.assignment.repository.TVShowRepository;

@Service
public class ShowService {
	
@Autowired
TVShowRepository tvShowRepo;

//getting all records from TVShows
public List<TVShow> getAllShows(){
	List<TVShow> shows = new ArrayList<>();
	tvShowRepo.findAll().forEach(show->shows.add(show));
	return shows;
}

public TVShow getShowById(int id) {
	return tvShowRepo.findById(id).get();
}

public List<TVShow> getShowByTitle(String title) {
	return getAllShows().parallelStream().filter(show->show.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
}

public List<TVShow> getShowByTitleAndSeason(String title,int season) {
	return getShowByTitle(title).parallelStream().filter(show->show.getSeason()==season).collect(Collectors.toList());
}

public List<TVShow> getShowByIds(List<Integer> ids) {
    List<TVShow> list = new ArrayList<>();
    tvShowRepo.findAllById(ids).iterator().forEachRemaining(list::add);
	return list;
}

public void saveOrUpdate(TVShow tvShow) {
	tvShowRepo.save(tvShow);
}

//deleting a specific record  
public void delete(int id)   
{  
	tvShowRepo.deleteById(id);  
}  

}
