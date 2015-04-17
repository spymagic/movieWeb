package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.neu.cs5200.model.Admin;
import edu.neu.cs5200.model.User;

import edu.neu.cs5200.model.AdminDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher dispatcher = null;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminDao dao = new AdminDao();
		int adminId = dao.findAdminId(username);
		Admin admin = new Admin();
		User user = new User();
		// get rid of the cast problem, transfer user instead of admin
		user.setUsername(username);
		user.setPassword(password);
		user.setId(adminId);
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setId(adminId);
		boolean checkUser=dao.verifyAdmin(admin);
		
		if(checkUser) {
			HttpSession session = request.getSession(true);
			session.setAttribute("currentUser", user);
			dispatcher = request.getRequestDispatcher("console.jsp");
			dispatcher.forward(request, response);

		} else {
			HttpSession session = request.getSession(false);
			session.setAttribute("currentUser", null);
			dispatcher = request.getRequestDispatcher("adminLogin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
