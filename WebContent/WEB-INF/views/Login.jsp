<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">
<title>Login</title>
</head>
<body>
<h3>Welcome to WeatherOrNot</h3>
<p>When you are getting ready always listen to your Mother....<br>
            ....Mother Nature
</p>

${msg}

<form action="existingUserLogin"> 
        <input type="email" placeholder="E-mail" name = "email" required><br>
        <input type="password" placeholder="Password" name="password" required><br><br>
        <input type="submit" value = "Continue">
</form><br>
<h3>New To WeatherOrNot?</h3><br>
<form action="getsignup">
	<button type="submit">Sign Up</button>
</form>
</body>
</html>