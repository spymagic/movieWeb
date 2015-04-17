package edu.neu.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MovieDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("MovieWeb");
	EntityManager em = null;
	
	public MovieDao() {
		em=factory.createEntityManager();
	}
	
	public Movie findMovieByTitle(String title){
		  Query q = em.createQuery("select M from Movie m where m.title= :title"); 
		  q.setParameter("title", title);
		  Movie movie = (Movie) q.getSingleResult();
		  return movie;
	}
	
	public void createMovie(Movie movie){

		em.getTransaction().begin();
		em.persist(movie) ;
		em.getTransaction().commit();

	}
	
	public List<Movie> findAllMovies()
	{
		List<Movie> movies = new ArrayList<Movie>();
		Query query = em.createQuery("select m from Movie m");
		movies =query.getResultList();	
		return movies;
	}
	
	public void removeMovieById(int id){
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		em.remove(movie);
		em.getTransaction().commit();

	}
	
	public Movie findMovieById(int id){
		Movie movie = em.find(Movie.class, id);

		return movie;
	}
	
	public List<Movie> MovielikedByUser(User user){
		List<Movie> movies = new ArrayList<Movie>();
		Query query = em.createQuery("select m from Movie m join m.users u where u=?1");
		query.setParameter(1,user);
		movies =query.getResultList();	
	
		return movies;
	}
	
	public void createComment(String userId, String movieId, String cont){
		
	}
	
/*	public List<Movie> searchMovie(String input){
		List<Movie> movies = new ArrayList<Movie>();
		Query query = em.createQuery("select m from Movie m where m.title = ?1");
		query.setParameter(1,"input");
		movies = query.getResultList(); 
		return movies;
	}
*/	
	
	
	public static void main(String[] args) {

	
	
	}
}
