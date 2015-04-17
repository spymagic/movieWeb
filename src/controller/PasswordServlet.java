package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.neu.cs5200.model.User;
import edu.neu.cs5200.model.UserDao;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		UserDao userDao = new UserDao();
		int userId = userDao.findUserId(username);
		User user = new User();
		user.setUsername(username);
		user.setPassword(oldPassword);
		user.setId(userId);
		boolean checkUser=userDao.verifyUser(user);
		System.out.print(checkUser);
		
		if(checkUser) {
			userDao.changeUserPassword(user, newPassword);
			HttpSession session = request.getSession(true);
			session.setAttribute("currentUser", user);
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		} else {
			HttpSession session = request.getSession(false);
			session.setAttribute("currentUser", user);
			dispatcher = request.getRequestDispatcher("profileEdit.jsp");
			dispatcher.forward(request, response);
		}
	}

}
