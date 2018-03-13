<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Top</title>
</head>
<body>
<h3>Top</h3>
<p>Upload a photo of the item.</p>
<form action="addItem" method="post" enctype="multipart/form-data">
  <input type="text" placeholder="File Upload">
  <input type="file" accept="image" name ="imageURL"><br><br><br>
  
  <p>Choose the type of top being added to the closet:</p>
  <input type="radio" name="type" value="tankTop">Tank-top<br>
  <input type="radio" name="type" value="tshirt">T-shirt<br>
  <input type="radio" name="type" value="longSleeve">Long sleeve<br>
  <input type="radio" name="type" value="blouse">Blouse<br>
  <input type="radio" name="type" value="buttonDown">Button-down<br>
  <input type="radio" name="type" value="collared">Collared<br>
  <input type="radio" name="type" value="athletic">Athletic wear<br><br>
  <input type = "hidden" name ="category" value ="TOP">

  <textarea placeholder="Add a brief items description..." name="description" size="20"></textarea><br>
<button type="submit">Add Item</button>
<button type="submit">Cancel</button>
</form>
</body>
</html>