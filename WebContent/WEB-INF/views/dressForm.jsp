<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">
<title>Dress</title>
</head>
<body>
<h3>Dress</h3>
<p>Upload a photo of the item.</p>
<form action="addItem" method="post" enctype="multipart/form-data">
  <input type="text" placeholder="File Upload">
  <input type="file" accept="image" name =	"imageURL"><br>


  <p>Choose the type of dress being added to the closet:</p>
  <input type="radio" name="type" value="maxi">Maxi<br>
  <input type="radio" name="type" value ="fancy">Fancy<br>
  <input type="radio" name="type" value="party">Party<br>
  <input type="radio" name="type" value="mindy">Mindy<br>
  <input type="radio" name="type" value="flare">Flare<br>
  <input type="radio" name="type" value="gown">Gown<br><br>
  <input type = "hidden" name ="category" value = "DRESS">
  
  
<textarea name="description" placeholder="Add a brief item description..."></textarea><br>  

<button type="submit">Add Item</button>
<button type="submit">Cancel</button>

</form>

</body>
</html>