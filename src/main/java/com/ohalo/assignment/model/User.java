package com.ohalo.assignment.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@Column 
	private String id;
	
	@Column
	private String name;
	
	@ManyToMany
	Set<TVShow> likedShows;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TVShow> getLikedShows() {
		return likedShows;
	}

	public void setLikedShows(Set<TVShow> likedShows) {
		this.likedShows = likedShows;
	}

}
