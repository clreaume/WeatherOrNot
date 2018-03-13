<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">
<title>Hamper</title>
</head>
<body>
	
	<h1>${name}'s Hamper</h1>

	<form action="home">
		<input type="submit" value="Get my outfit">
	</form>

	<form action="addToCloset">
		<input type="submit" value="Add item">
	</form>

	<form action="viewCloset">
		<input type="submit" value="View closet">
	</form>


	<c:forEach var="category" items="${hamperItems}">


			
			<h2>${category.key}</h2>




			<c:choose>
				<c:when test="${category.value.size() > 0}">
					<table border="1">
									<!-- only  options for border are 1 or 0 -->
					<c:forEach var="item" items="${category.value}">
						<!-- var is what gets reassigned every iteration -->
						<tr>
							<td><img alt="" src="${item.imageURL}" height="100px"
								width="100px"></td>
							<%-- <td>${ item.cat}</td>
							<td>${ item.type}</td> --%>
							<td>${ item.description}</td>
							<td><a href="deleteItem?id=${item.itemId}">Delete</a></td>
							<!-- THIS IS URL ENCODING -->
							<td><a href="putInCloset?id=${item.itemId}">Put In Closet</a></td>
							<!-- can use ampersand (maybe two?) to add more params to pass in -->
						</tr>
					</c:forEach> 

					</table>

				</c:when>

				<c:when test="${category.value.size() == 0 }">

					<p>None in the hamper</p>

				</c:when>


			</c:choose>


		</c:forEach>
		
	
	
</body>
</html>