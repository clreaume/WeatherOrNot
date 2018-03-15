<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">

<link rel="stylesheet" href="resources/style.css">
<title>Login</title>
</head>
<body>

<h3>Welcome to Weather Or Not</h3> 
<p>When you are getting ready always listen to your Mother...</p>
<p>...Mother Nature</p>

${msg}

<form action="existingUserLogin" class="existingUserLoginForm"> 
        <input type="email" placeholder="E-mail" name = "email" required><br>
        <input type="password" placeholder="Password" name="password" required><br><br>
        <input type="submit" value = "Continue">
</form>
<br><br>
<h3>New To WeatherOrNot?</h3><br>
<form action="getsignup">
	<input type="submit" value = "Sign Up">
</form>
</body>
</html>