package edu.neu.cs5200.model;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity
public class Comment implements Serializable {
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String content;
	@Temporal( TemporalType.DATE )
	private Date DateOfComment;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@ManyToOne
	@JoinColumn(name="movieId")	
	private Movie movie;
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Date getDateOfComment() {
		return this.DateOfComment;
	}

	public void setDateOfComment(Date DateOfComment) {
		this.DateOfComment = DateOfComment;
	}   
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}   
	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
