package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.model.UserDao;

/**
 * Servlet implementation class unFollowServlet
 */
@WebServlet("/unFollowServlet")
public class unFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int followerId = Integer.parseInt(request.getParameter("followerId"));
		int userId1 = Integer.parseInt(request.getParameter("userId1"));

		UserDao dao = new UserDao();
		dao.UserUnFollowUser(userId1, followerId);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp"); 
		dispatcher.forward(request, response);
}
}
