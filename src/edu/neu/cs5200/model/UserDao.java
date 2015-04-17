package edu.neu.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import edu.neu.cs5200.model.User;

public class UserDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("MovieWeb");
	EntityManager em = null;
	
	public UserDao() {
		em=factory.createEntityManager();
	}
    
	public boolean createUser(User user){
		boolean check = true;
//		UserDao dao = new UserDao();
//		user = dao.findUserByName(user.getUsername());
//		if(user==null){
//		check = true;			
		em.getTransaction().begin();
		em.persist(user) ;
		em.getTransaction().commit();

//		return check;
//		}
//		else{
			return check;
//		}
	}
	
	public void changeUserPassword(User user, String newPassword){
		em.getTransaction().begin();
        user.setPassword(newPassword);
        em.merge(user);
		em.getTransaction().commit();

	}
	
	public User findUserById(int id){
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		em.getTransaction().commit();
		return user;
	}
	
	public User findUserByName(String username){
		  Query q = em.createQuery("select u from User u where u.username= :username"); 
		  q.setParameter("username", username);
		  User user = (User) q.getSingleResult();
		  return user;
	}
	
	public void removeUserById(int id){
		em.getTransaction().begin();
		User user = em.find(User.class, id);
		em.remove(user);
		em.getTransaction().commit();

	}

	public List<User> findAllUsers()
	{
		List<User> users = new ArrayList<User>();
		Query query = em.createQuery("select u from User u");
		users = query.getResultList();	
		return users;
	}
	
	public int findUserId(String username) 
	{
		int userId = 0;
		List<User> users = new ArrayList<User>();
        UserDao dao=new UserDao();
		users = dao.findAllUsers();
		for (User user: users)
		{
			if (user.getUsername().equals(username))
				userId = user.getId();
		}

		return userId;
	}
	public boolean verifyUser(User user)
	{
		boolean checkUser = false;
		System.out.print(user);
		int Id = findUserId(user.getUsername());
		User realUser = em.find(User.class, Id);
		System.out.println(realUser);

		if(realUser != null)
		{
			if (user.getPassword().equals(realUser.getPassword()))
				checkUser = true;
		}
		return checkUser;
	}
	
	public void UserLikeMovie(int userId, int movieId){
		em.getTransaction().begin();
		
		User user = em.find(User.class, userId);
		user.addMovie(em.getReference(Movie.class, movieId));
		em.getTransaction().commit();
	}
	
	public void UserUnLikeMovie(int userId, int movieId){
		em.getTransaction().begin();
		
		User user = em.find(User.class, userId);
		user.removeMovie(em.getReference(Movie.class, movieId));
		em.getTransaction().commit();
	}
	
	public boolean VerifyLikeStatue(int userId, int movieId){
		User user = em.find(User.class, userId);
		Movie movie = em.find(Movie.class, movieId);

		if(user.verifyExist(movie)==false){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean VerifyFollowerStatue(int userId, int followerId){
		User user = em.find(User.class, userId);
		User follower = em.find(User.class, followerId);

		if(user.verifyExistFollower(follower)==false){
			return true;
		}
		else{
			return false;
		}
	}

	
	public void UserFollowUser(int userId, int followerId){
		if(userId!=followerId){
		em.getTransaction().begin();
		User user = em.find(User.class, followerId);
		user.followUser(em.getReference(User.class, userId));
		em.getTransaction().commit();
	}
	}
	
	public void UserUnFollowUser(int userId, int followerId){
		if(userId!=followerId){
		em.getTransaction().begin();
		User user = em.find(User.class, followerId);
		user.unFollowUser(em.getReference(User.class, userId));
		em.getTransaction().commit();
	}
	}
	
	
	public List<User> listFollowers(User user){
		List<User> users = new ArrayList<User>();
		Query query = em.createQuery("select u from User u join u.users uu where uu=?1");
		query.setParameter(1,user);
		users =query.getResultList();	
	
		return users;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        UserDao dao = new UserDao();
        
/*        User u1 = new User("Jack", "123", "jack@gmail.com");
        User u2 = new User("David", "123", "david@gmail.com");
        User u3 = new User("Amy", "123", "amy@gmail.com");
        User u4 = new User("Song", "123", "song@gmail.com");
        User u5 = new User("Li", "123", "Li@gmail.com");
*/        
//        dao.createUser(u1);
//        dao.createUser(u2);
//        dao.createUser(u3);
//        dao.createUser(u4);
//        dao.createUser(u5);

//        dao.changeUserPassword(u3, "123");
        
//        User s = dao.findUserById(1);
//        User s = dao.findUserByName("Jack");
//        System.out.print(s.getUsername());
        	List<User> users = dao.findAllUsers();
        	System.out.print(users);
        
//        int id=dao.findUserId("song");
//        System.out.print(id);

//		User user = new User();
//		user.setUsername("song");
//		user.setPassword("123");
//		System.out.println(user.getUsername());
//        boolean checkUser=dao.verifyUser(user1);
        
 //       List<Movie> movies = dao.MovielikedByUser(user);
//        System.out.print(movies);
	}
 
}
