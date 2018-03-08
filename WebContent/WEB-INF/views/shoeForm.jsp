<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shoes Form</title>
</head>
<body>
<h3>Shoes</h3>
<form action="">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image"><br><br><br>
  <input type="radio" name="clothes" value="Sneakers">Sneaker<br>
  <input type="radio" name="clothes value="Boots">Boots<br>
  <input type="radio" name="clothes" value="Waterproof Boots">Waterproof Boots<br>
  <input type="radio" name="clothes value="Flat">Flat<br>
  <input type="radio" name="clothes value="Heels">Heels<br>
  <input type="radio" name="clothes value="Sandals">Sandals<br><br>
  <input type="radio" name="clothes value="Dress Shoes">Dress Shoes<br><br>


  <textarea name="description" size="20">
  	Add a brief items description
  </textarea><br>
<button type="submit">Add Item</button>
<button type="submit">Cancel</button>



</form>
</body>
</html>