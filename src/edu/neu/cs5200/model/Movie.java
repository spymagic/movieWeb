package edu.neu.cs5200.model;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity

public class Movie implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String year;
	String title;   
	String thumbnail;
	String rating;
	String runtime;
	String synopsis;
	
	@OneToMany(mappedBy="movie")
	List<Actor> actors;
	
	@ManyToMany(mappedBy="movies")
	private List<User> users;	
	
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getYear() {
		return year;
	}
	public String getRating() {
		return rating;
	}

	@OneToMany(mappedBy="movie", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments; 
	
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	private static final long serialVersionUID = 1L;

	public Movie() {
		super();
	}  
	
	public Movie(String title, String year,String thumbnail,  String synopsis,String rating, String runtime) {
		
		this.title = title;
		this.year = year;
		this.thumbnail= thumbnail;
		this.rating = rating;
		this.synopsis = synopsis;
		this.runtime = runtime;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   


	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
   
}
