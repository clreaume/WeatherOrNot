<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
table border="1">
		<!-- only options for border are 1 or 0 -->
		<c:forEach var="myVar" items="${clothes}">
			<!-- var is what gets reassigned every iteration -->


			<tr>
			<td><img alt="" src=myVar.imageURL></td>
				<td>${ myVar.cat}</td>
				<td>${ myVar.type}</td>
				<td>${ myVar.description}</td>
				<td><a href ="deleteItem?id=${myVar.itemID}">Delete</a></td> <!-- THIS IS URL ENCODING -->
				<td><a href ="putInHamp?id=${myVar.itemID}">Put In Hamper</a></td> <!-- can use ampersand (maybe two?) to add more params to pass in -->
			</tr>


		</c:forEach>




	</table>
</body>
</html>