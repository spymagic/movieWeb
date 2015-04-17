<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <div class="container">
    <form method="post" action="RegisterServlet" >
      <h1>Register</h1>

		<p>Username:<input name="username" class="form-control" /></p>
		<p>Password:<input name="password" type="password" class="form-control"/></p>
		<P>Email:<input name="email" class="form-control" /></p>
		<p>First Name:<input name="firstName" class="form-control" /></p>
		<p>Last Name:<input name="lastName" class="form-control" /></p>
		<p>Phone Number:<input name="phoneNumber" class="form-control" /></p>
		
		<button name="action" value="register" class="btn btn-primary btn-block">Register</button>
    </form>
    </div>
    </form>
</body>
</html>