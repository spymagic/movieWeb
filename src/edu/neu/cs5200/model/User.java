package edu.neu.cs5200.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User extends People {


	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	

	@ManyToMany
    @JoinTable(name="User_Movie",
        joinColumns=
            @JoinColumn(name="UserId", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="MovieId", referencedColumnName="ID")
        )
	private List<Movie> movies;
	
	//user and followers, self reference
	@ManyToMany
	@JoinTable(name="User_User",
		joinColumns=
			@JoinColumn(name="UserId", referencedColumnName="ID"),
	    inverseJoinColumns=
            @JoinColumn(name="FollowId", referencedColumnName="ID")
        )			

	private List<User> users;	
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true	)
	private List<Comment> comments;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addMovie(Movie movie) {
		if(this.movies.contains(movie)==false)
		this.movies.add(movie);
	}
	
	public void removeMovie(Movie movie){
		if(this.movies.contains(movie)){
			this.movies.remove(movie);
		}
	}
	
	public boolean verifyExist(Movie movie){
		if(this.movies.contains(movie)==true){
			return true;
		}
		return false;
	}
	
	public boolean verifyExistFollower(User user){
		if(this.users.contains(user)==true){
			return true;
		}
		return false;
	}
	
	public void followUser(User user){
		if(this.users.contains(user)==false){
			this.users.add(user);
		}
	}
    
	public void unFollowUser(User user){
		if(this.users.contains(user)==true){
			this.users.remove(user);
		}
	}

}

