<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>${name}'s Closet</h1>

	<table border="1">
		<!-- only options for border are 1 or 0 -->
		<c:forEach var="myVar" items="${clothes}">
			<!-- var is what gets reassigned every iteration -->


			<tr>
				<td><img alt="" src="${myVar.imageURL}" height="100px" width="100px"></td>
				<%-- <td>${ myVar.cat}</td> --%>
				<td>${ myVar.type}</td>
				<td>${ myVar.description}</td>
				<td><a href="deleteItem?id=${myVar.itemId}">Delete</a></td>
				<!-- THIS IS URL ENCODING -->
				<td><a href="putInHamp?id=${myVar.itemId}">Put In Hamper</a></td>
				<!-- can use ampersand (maybe two?) to add more params to pass in -->
			</tr>


		</c:forEach>

	</table>
	
	
	<form action = "home">
	<input type = "submit" value = "Get my outfit">
	</form>
	
	<form action = "addToCloset">
	<input type = "submit" value = "Add item">
	</form>
	
	<form action ="viewHamp">
	<input type = "submit" value = "View Hamper">
	</form>
	
</body>
</html>