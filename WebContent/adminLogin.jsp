<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css">
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>

<div class="container">

	<h1>Admin Login</h1>
	<form action="AdminLoginServlet" method="post">
		Admin Name:
		<input name="username" class="form-control"/>
		
		Password:
		<input name="password" type="password"  class="form-control"/>
		
		<button class="btn btn-primary btn-block">Login</button>
	</form>	
</div>

</body>
</html>