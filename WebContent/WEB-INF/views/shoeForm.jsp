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
<form action="addItem">
  <input type="text" placeholder="File Upload" >
  <input type="file" name="pic" accept="image" name = "imageURL"><br><br><br>
  
  <input type="radio" name="type" value="sneakers">Sneaker<br>
  <input type="radio" name="type" value="boots">Boots<br>
  <input type="radio" name="type" value="waterproofBoots">Waterproof Boots<br>
  <input type="radio" name="type" value="flats">Flats<br>
  <input type="radio" name="type" value="heels">Heels<br>
  <input type="radio" name="type" value="sandals">Sandals<br><br>
  <input type="radio" name="type" value="dressShoes">Dress Shoes<br><br>
  <input type = "hidden" name ="category" value = "SHOE">
  


  <textarea name="description" size="20">
  	Add a brief items description
  </textarea><br>
<button type="submit">Add Item</button>
<button type="submit">Cancel</button>



</form>
</body>
</html>