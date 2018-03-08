<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dress Form</title>
</head>
<body>
<h3>Dress</h3>
<form action="addItem">
  <input type="text" placeholder="File Upload" >
  <input type="file" name="pic" accept="image" name = "imageURL"><br><br>
  <input type="text" placeholder="Give the Item name">

  
  <input type="radio" name="type" value="maxi">Maxi<br>
  <input type="radio" name=type" value ="fancy">Fancy<br>
  <input type="radio" name="type" value="party">Party<br>
  <input type="radio" name="type" value="mindy">Mindy<br>
  <input type="radio" name="type" value="flare">Flare<br>
  <input type="radio" name="type" value="gown">Gown<br><br>
  <input type = "hidden" name ="category" value = "DRESS">
  
  
<textarea name="description" >Add a brief item description...</textarea>
  

<button type="submit">Add Item</button>
<button type="submit">Cancel</button>



</form>
</body>
</html>