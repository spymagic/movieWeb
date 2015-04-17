<%@ page language="java" import="java.util.*,edu.neu.cs5200.model.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
    User user = (User)session.getAttribute("currentUser");


%>
  <head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">
  

    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
      <a class="navbar-brand" href="#">Movie Share</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="login.jsp">Login <span class="sr-only">(current)</span></a></li>
        <li><a href="register.jsp">Register</a></li>
        <%if (user!=null){ %>
        <li><a href="profile.jsp">Profile</a></li>
        <%} 
        else{ %>
        <li><a href="login.jsp">Profile</a></li>
        <%} %>
      </ul>
      <form action="search.jsp" method="post" class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" name="input" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="adminLogin.jsp">Admin Login</a></li>
        <%if (user!=null){ %>
        <li><a href="#"><%=user.getUsername() %></a></li>
        <li><a href="logout.jsp">logout</a></li>
        
        <%} %>
      </ul>
    </div>
  </div>
</nav>
<div class="jumbotron" style="padding:5% 10%;">
  <h1>Movie Share</h1>
  <p>This is a simple movie share website, people will share their favoriate movies here!</p>
  <p><a class="btn btn-primary btn-lg" style="border-radius:9px;">Learn more</a></p>
</div>
 
    <!-- list all movies -->
<table class="table">
	<tbody>
 		<tr>All Movies:</tr>
 		
<%		
	 MovieDao movieDao = new MovieDao();
     List<Movie> movies = movieDao.findAllMovies();
//     MovieJsonWebService movieweb =new MovieJsonWebService();

     for (Movie movie: movies) {
//		Movie movieObject = movieweb.getMovieforName(movie.getTitle());
%>    	
<td>
<% if (user!= null){
%>

<a href = "detail.jsp?movieId=<%=movie.getId()%>&userId=<%=user.getId() %>&title=<%= movie.getTitle() %>"><%= movie.getTitle() %></a>
<% }
else { %>
<a href = "detail.jsp?movieId= <%= movie.getId() %> &title= <%= movie.getTitle() %> " ><%= movie.getTitle() %></a>
<% } %>
              
      

</td>
<%} %> 

	</tbody>
</table>	

<!-- list all users -->

<table class="table">
	<tbody>
 		<tr>All Users:</tr>
 		
<%		
	 UserDao userDao = new UserDao();
     List<User> users = userDao.findAllUsers();

     for (User user1: users) {
//		Movie movieObject = movieweb.getMovieforName(movie.getTitle());
%>    	
<td>
<% if (user!= null){
%>

<a href = "profile.jsp?userId=<%=user1.getId() %>"><%= user1.getUsername() %></a>
<% }
else { %>
<a href = "detail.jsp?"><%=user1.getUsername() %> </a>
<% } %>
              
      

</td>
<%} %> 

	</tbody>
</table>	
</body>

</html>
