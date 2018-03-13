<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">
<title>Sign Up Form</title>
</head>
<body>

<h3>Join WeatherOrNot today</h3>
	<form action = "createUser" method="post">
	     
        <input type="text" placeholder="First Name" name="fname" required><br>
        
        <input type="text" placeholder="Last Name" name="lname" required><br>
        
        <input type="email" placeholder="Email" name="email" required><br>
        
        <input type="password" placeholder="Password" name="password" required><br><br>
        
        <button type="submit" >Sign Up</button>
</form>

<br>

<p>By signing up, you agree to the Terms of Service and Privacy Policy,incling Cookie Use. </p>
</body>
</html>