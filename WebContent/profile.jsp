<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    User user = (User)session.getAttribute("currentUser");
%>
<head>
<title>Profile</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
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
      <form action="search.jsp" method="post" class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="adminLogin.jsp">Admin Login</a></li>
        
        <li><a href="#"><%=user.getUsername() %></a></li>

        
      </ul>
    </div>
  </div>
</nav>
<div class="container">

<%	    String userid1= request.getParameter("userId1");
		User user1;
		if(userid1!=null){
	    int userID = Integer.parseInt(userid1);
				
		UserDao dao1= new UserDao();
	    user1 = dao1.findUserById(userID);
		}
		else{
			user1=user;
		}
 %>
 <h1>Profile of <%=user1.getUsername()%></h1>
 
 			<% UserDao userdao = new UserDao();
               boolean statue = userdao.VerifyFollowerStatue(user.getId(), user1.getId());				
			   if(statue){%>
 	<!-- follow button -->
 		<form action="followServlet" style="float:left;">
			<input type ="submit" value="Follow" class="btn btn-primary" style="display:block;width:100px;">
    		<input type="hidden" name="followerId" value="<%=user.getId()%>">	
    		<input type="hidden" name="userId1" value="<%=userid1%>">   	   
		</form>
				<%} 
				
				else{%>
	<!-- unfollow button -->
		<form action="unFollowServlet" style="float:left;">
			<input type ="submit" value="UnFollow" class="btn btn-primary"style="display:block;width:100px;">
    		<input type="hidden" name="followerId" value="<%=user.getId()%>">	
    		<input type="hidden" name="userId1" value="<%=userid1%>">   	   
		</form>
		      <%} %>
		
<div class="clearfix" style="clear:both;"></div>
	<!-- edit button -->	
		<form action="profileEdit.jsp" >
			<input type = "submit" value="Change Password" class="btn btn-danger" style="margin-top:10px">
    	  	   
		</form>
		
<div style="width:100%; ">
 <table class="table" style="float:left; display:inline-block; width:400px;" >
 <thead>
  <tr>
   <th>Movie Liked:</th>
  </tr>
 </thead> 
   <%
 		MovieDao dao = new MovieDao();  
     	List<Movie> movies = dao.MovielikedByUser(user1); 
 	 	for(Movie movie:movies){
	%>
    <tr>
      <td>
         <a href="detail.jsp?movieId=<%=movie.getId()%>&userId=<%=user.getId()%>&title=<%=movie.getTitle()%>"><%= movie.getTitle() %></a>
      </td>
	</tr>
	<%}%>
 </table>
 


 <table class="table" style="width:400px; float:right; display:inline-block;">
  <thead>
    <tr>
  		<th>Followers:</th>
    </tr>
  </thead>
	<%	
	UserDao userDao= new UserDao();
	List<User> users = userDao.listFollowers(user1);
	for(User user2: users){
	%>
	<tr>
		<td>
			<a href="profile.jsp?userId1=<%=user2.getId()%>"><%=user2.getUsername()%></a>
		</td>
	</tr>
	<%}%>
	
</table>

  </div>


</div>


</body>
</html>
