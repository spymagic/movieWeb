<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">

<%
    User user = (User)session.getAttribute("currentUser");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Edit</title>
</head>
<body>
<div class="container">

	<h1>Password Change</h1>
	<form action="PasswordServlet" method="post">
		Old Password:
		<input name="oldPassword" type="password"  class="form-control"/>
		
		New Password:
		<input name="newPassword" type="password"  class="form-control"/>
		
		<input type="hidden" name="username" value="<%=user.getUsername()%>"/>
		
		<button class="btn btn-primary btn-block">Submit</button>
	</form>	
</div>

</body>
</html>