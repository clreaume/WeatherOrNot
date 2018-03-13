<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">
<title>Weather Or Not</title>
</head>
<body>
<h2>Welcome back ${msg}!</h2>
<br>
<h3>${cityState} : ${temp}°</h3> 
<img alt="pic of current weather" src="${icon_url}">


<form action = "home">
<input type="submit" value="Get outfit" >
</form>

<form action = "addToCloset">
<input type="submit" value="Add item to closet" >
</form>

<form action = "viewCloset">
<input type="submit" value="View closet" >
</form>

<form action = "viewHamp">
<input type="submit" value="View hamper">
</form>



</body>
</html>