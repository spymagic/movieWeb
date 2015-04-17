package edu.neu.cs5200.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;




public class CommentDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("MovieWeb");
		
	public void createComment(int userId, int movieId, Comment comment){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		User user = em.find(User.class, userId);
		Movie movie = em.find(Movie.class, movieId);
		
		comment.setUser(user);
		comment.setMovie(movie);

		user.getComments().add(comment);
		
		
		movie.getComments().add(comment);
		
		Date now = new Date(); 
		comment.setDateOfComment(now);;
		
		em.merge(comment);
		em.merge(movie);
		em.merge(user);
		em.getTransaction().commit();
		em.close();
        
	}
	
	public List<Comment> listAllComments()
	{
		List<Comment> comments = new ArrayList<Comment>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select comment from Comment comment");
		comments = (List<Comment>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return comments;
	}
	
	public List<Comment> ListCommentsForMovie(String movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		EntityManager em = factory.createEntityManager();
		int movieId1 = Integer.parseInt(movieId); 
		Movie movie = em.find(Movie.class, movieId1);
		comments = movie.getComments();

		return comments;
	}
	
	public User findUserIdByComment(Comment comment){
		User user = comment.getUser();
//		String userId = String.valueOf(user.getId());
		
		return user;
	}
	
	public void removeCommentById(int id){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Comment comment = em.find(Comment.class, id);
		em.remove(comment);
		em.getTransaction().commit();

	}
	
	
	public static void main(String[] args) {
		CommentDao dao = new CommentDao();
		dao.ListCommentsForMovie("1");
}
}	



	