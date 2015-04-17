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
 * Servlet implementation class LikeServlet
 */
@WebServlet("/UnLikeServlet")
public class UnLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int movieId = Integer.parseInt(request.getParameter("movieId"));
		int userId = Integer.parseInt(request.getParameter("userId"));

		UserDao dao = new UserDao();
		dao.UserUnLikeMovie(userId, movieId);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp"); 
		dispatcher.forward(request, response);
}
}
