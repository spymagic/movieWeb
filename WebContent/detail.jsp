<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.model.*, java.util.*, javax.servlet.RequestDispatcher,javax.servlet.ServletException,
 javax.servlet.http.HttpServlet,
 javax.servlet.http.HttpServletRequest,
 javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    User user = (User)session.getAttribute("currentUser");
    
    if (user == null){
    response.sendRedirect("/MovieWeb/login.jsp");
    }
    else{
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp">Movie Share</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="login.jsp">Login <span class="sr-only">(current)</span></a></li>
        <li><a href="register.jsp">Register</a></li>
        <li><a href="profile.jsp">Profile</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="adminLogin.jsp">Admin Login</a></li>
        <li><a href="#"><%=user.getUsername() %></a></li>
        <li><a href="logout.jsp">logout</a></li>
        
        
      </ul>
    </div>
  </div>
</nav>
 
    <!-- Page Content -->
    <div class="container">
			
	<%
		String movieId=request.getParameter("movieId");// movie id
		String title=request.getParameter("title");  // movie title
	
		MovieJsonWebService movies =new MovieJsonWebService();
		Movie movieObject = movies.getMovieforName(title);
		
		ActorDao daoactor = new ActorDao();
		List<Actor> actors = daoactor.ListActorsForMovie(movieId);
		
	%>
        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->

                <!-- Title -->
                <h1>Movie Details for <%=movieObject.getTitle() %></h1>
				
				<!-- verify the like or unlike statue -->
				<% UserDao userdao = new UserDao();
                  boolean statue = userdao.VerifyLikeStatue(user.getId(), Integer.parseInt(movieId));				
				if(statue){%>
                <form action="LikeServlet" style="float:left;">
					<input type = "submit" value="Like" class="btn btn-primary" style="display:block;width:100px;">
					<input type="hidden" name="movieId" value="<%=movieId %>">
					<input type="hidden" name="userId" value="<%=user.getId() %>">
					<input type="hidden" name="title" value="<%=title %>">
    
				</form>
				<%} 
				
				else{%>
				<form action="UnLikeServlet" style="float:left;">
					<input type = "submit" value="UnLike" class="btn btn-primary" style="display:block;width:100px;margin-left:5px;">
					<input type="hidden" name="movieId" value="<%=movieId %>">
					<input type="hidden" name="userId" value="<%=user.getId() %>">
					<input type="hidden" name="title" value="<%=title %>">
    
				</form>

                <%} %>

                <!-- Post Content -->
                <table class="table">
					<tbody>
						<tr><td>Title:</td><td><%= movieObject.getTitle() %></tr>
						<tr><td>Year:</td><td><%= movieObject.getYear() %></tr>
						<tr><td>Rating:</td><td><%= movieObject.getRating() %></tr>
						<tr><td>Runtime:</td><td><%= movieObject.getRuntime() %></tr>
						<tr><td>Synopsis:</td><td><%= movieObject.getSynopsis() %></tr>
						<tr><td>Actors:</td><td>
						<%for(Actor actor: actors){ %>
						<%=actor.getName()%>&nbsp
						<%} %>
						</tr>
					</tbody>
				</table>
                <hr>

				<!-- Posted Comments -->

                <!-- Comment -->
				    <%
					CommentDao dao=new CommentDao();
					List<Comment> comments= dao.ListCommentsForMovie(movieId); 
					for(Comment comment:comments){
						User user1 = dao.findUserIdByComment(comment);
					%>
                <div class="media">
                    
                    <div class="media-body">
                        <h4 class="media-heading"><a href="profile.jsp?userId1=<%=user1.getId()%>"><%out.print(user1.getUsername());%></a>
							<% Date now = comment.getDateOfComment();
							
							%>
                            <small><%=String.format("%tD, %<tR",now) %></small>
                        </h4>
								<%=comment.getContent() %>
						</div>
                </div>
					<%} %>





<br>

                <!-- Blog Comments -->

                <!-- Comments Form -->
                <div class="well">
                    <h4>Leave a Comment:</h4>
                        <form action="CommentServlet" method="get">

                        <div class="form-group">
                            <input type="hidden" name="movieId" value="<%=movieId %>">
							<input type="hidden" name="userId" value="<%=user.getId() %>">
							<input type="hidden" name="title" value="<%=title %>">
							<textarea name="cont" rows="10" cols="80"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>

                <hr>

                			
								

                

            </div>
				<!-- Blog Sidebar Widgets Column -->
            <div class="col-md-4">
                <!-- Side Widget Well -->
                <div class="well">
                    <h4>Poster</h4>
                <img class="img-responsive" src="<%= movieObject.getThumbnail() %>" alt="">
                </div>

            </div>


        </div>
        <!-- /.row -->


        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Pengyi Song 2015</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>
<%} %>
</html>
