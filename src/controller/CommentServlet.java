package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.model.Comment;
import edu.neu.cs5200.model.CommentDao;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId=Integer.parseInt(request.getParameter("userId"));// user id
		int movieId=Integer.parseInt(request.getParameter("movieId"));// movie id
		String title=request.getParameter("title");  // movie title
		String content = request.getParameter("cont");   //get the content from detail.jsp

		Comment comment= new Comment();
		comment.setContent(content);
       
		CommentDao dao = new CommentDao();
		
		dao.createComment(userId, movieId, comment);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp"); 
		dispatcher.forward(request, response);

}
}