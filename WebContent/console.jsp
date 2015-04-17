<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.model.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    User user1 = (User)session.getAttribute("currentUser");
%>
<head>
<title>Admin</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="logout.jsp">Movie Share</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="#">Admin</a></li>
        <li><a href="logout.jsp">logout</a></li>
        
      </ul>


        
        
      </ul>
    </div>
  </div>
</nav>
<body>

    <div class="container">
    <h1>User and Movie DashBoard</h1>
    
    <%
    UserDao dao = new UserDao();
    MovieDao movieDao = new MovieDao();
    CommentDao commentDao = new CommentDao();
    String action = request.getParameter("action");
    String id = request.getParameter("id");
    String username  = request.getParameter("username");
    String password = request.getParameter("password");
    String title  = request.getParameter("title");
    
    if("createUser".equals(action))
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        dao.createUser(user);
    }
    else if("deleteUser".equals(action))
    {
        int idInt = Integer.parseInt(id);
        dao.removeUserById(idInt);   
    }
    
    else if("createMovie".equals(action))
    {
        Movie movie = new Movie();
        movie.setTitle(title);
        movieDao.createMovie(movie);
    }
    
    else if("deleteMovie".equals(action))
    {
        int idInt = Integer.parseInt(id);
        movieDao.removeMovieById(idInt);   
    }
    
    else if("deleteComment".equals(action))
    {
        int idInt = Integer.parseInt(id);
        commentDao.removeCommentById(idInt);   
    }
/*    else if("update".equals(action))
    {
        int idInt = Integer.parseInt(id);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        dao.updateUser(idInt, user);
    }
*/    
    
    List<User> users = dao.findAllUsers();
	List<Movie> movies = movieDao.findAllMovies();
	List<Comment> comments = commentDao.listAllComments();
    %>
    
    <!-- user control table -->
    <form action="console.jsp">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Phone</th>
                
            </tr>
        </thead>
        <tbody>
    <%
    for(User user : users)
    {
    %>
            <tr>
                <td><%=user.getId() %></td>
                <td><a href="profile.jsp?userId1=<%=user.getId()%>"><%=user.getUsername() %></a></td>
                <td><%=user.getEmail() %></td>
                <td><%=user.getFirstName() %></td>
                <td><%=user.getLastName() %></td>
                <td><%=user.getPhoneNumber() %></td>
                
                
                <td>
                    <a class="btn btn-danger" href="console.jsp?action=deleteUser&id=<%=user.getId() %>">Delete</a>

                </td>
            </tr>
        <%
    }
    %>
        </tbody>
  
    </table>
    </form>
    <br>
    <br>
    <br>
    
    <!-- movie control table -->
    <form action="console.jsp">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
            </tr>

        </thead>
        <tbody>
    <%
    for(Movie movie : movies)
    {
        %>
            <tr>
                <td><%=movie.getId() %></td>
                <td><a href="detail.jsp?movieId=<%=movie.getId()%>&userId=<%=user1.getId()%>&title=<%=movie.getTitle()%>"><%=movie.getTitle() %></a></td>
                <td>
                    <a class="btn btn-danger" href="console.jsp?action=deleteMovie&id=<%=movie.getId() %>">Delete</a>
                </td>
            </tr>
    <%
    }
    %>
        </tbody>
                <tr>
                <th><input class="form-control" name="title" placeholder="Enter Movie Title" /></th>
                <th>
                    <button class="btn btn-success" name="action" value="createMovie">Add</button>
                </th>
            </tr>
    </table>
    </form>
    <br>
    <br>
    
    
    <!-- comment control table -->
    <form action="console.jsp" >
        <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Date Of Comment</th>
                <th>Username</th>
                <th>Comment</th>
            </tr>

        </thead>
       <tbody>
       	<%for (Comment comment: comments){ %>
       		<tr>
       			<td><%=comment.getId()%></td>
       			<td><%=comment.getMovie().getTitle()%></td>
       			<td><%=comment.getDateOfComment() %></td>
       			<td><%=comment.getUser().getUsername() %></td>
       			<td><%=comment.getContent() %></td>
       			<td>
                    <a class="btn btn-danger" href="console.jsp?action=deleteComment&id=<%=comment.getId() %>">Delete</a>
                </td>
       		</tr>
       		<%} %>
       </tbody> 
    </form>
    
    
    </div>
</body>
</html>