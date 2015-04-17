<%@ page language="java" import="java.util.*,edu.neu.cs5200.model.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">

<%    User user = (User)session.getAttribute("currentUser");
%>
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
      <form action="search.jsp" method="post" class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" name="input" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="adminLogin.jsp">Admin Login</a></li>
        <%if (user!=null){ %>
        <li><a href="profile.jsp"><%=user.getUsername() %></a></li>
        <%} %>

      </ul>
    </div>
  </div>
</nav>
  <head>
    
    <title>search</title>
    
  </head>
  
  <body>

<div class="container">

	<h1>Search Results:</h1>


<%  MovieJsonWebService dao = new MovieJsonWebService();
	String input= request.getParameter("input");
//	Movie movie = dao.getMovieforName(input);
	
	MovieDao movieDao = new MovieDao();
	Movie movie = movieDao.findMovieByTitle(input);
	
//	for (Movie movie: movies){








if (user!=null){ %>
<%= movie.getTitle() %> <a href="detail.jsp?movieId=<%=movie.getId()%>&userId=<%=user.getId()%>&title=<%=movie.getTitle()%>">Detail</a><br>
<%} 
else{ %>
<%= movie.getTitle() %> <a href="login.jsp">Detail</a><br>
<% } %>


</div>
</body>
</html>
