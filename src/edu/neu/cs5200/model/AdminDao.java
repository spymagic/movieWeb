package edu.neu.cs5200.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import edu.neu.cs5200.model.Admin;

public class AdminDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("MovieWeb");
	EntityManager em = null;
	
	public AdminDao() {
		em=factory.createEntityManager();
	}

	public List<Admin> findAllAdmins()
	{
		List<Admin> admins = new ArrayList<Admin>();
		Query query = em.createQuery("select a from Admin a");
		admins = query.getResultList();	
		return admins;
	}
	
	public int findAdminId(String username) 
	{
		int adminId = 0;
		List<Admin> admins = new ArrayList<Admin>();
        AdminDao dao=new AdminDao();
		admins = dao.findAllAdmins();
		for (Admin admin: admins)
		{
			if (admin.getUsername().equals(username))
				adminId = admin.getId();
		}
		return adminId;
	}
	public boolean verifyAdmin(Admin admin)
	{
		boolean checkAdmin = false;
		int Id = findAdminId(admin.getUsername());
		Admin realAdmin = em.find(Admin.class, Id);
		System.out.println(realAdmin);

		if(realAdmin != null)
		{
			if (admin.getPassword().equals(realAdmin.getPassword()))
				checkAdmin = true;
		}
		return checkAdmin;
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
 
}
