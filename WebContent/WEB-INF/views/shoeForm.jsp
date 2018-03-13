<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shoes</title>
</head>
<body>
<h3>Shoes</h3>
<p>Upload a photo of the item.</p>
<form action="addItem" method="post" enctype="multipart/form-data">
  <input type="text" placeholder="File Upload" >
  <input type="file" accept="image" name = "imageURL"><br><br><br>
  <p>Choose the type of shoes being added to the closet:</p>
  <input type="radio" name="type" value="sneakers">Sneaker<br>
  <input type="radio" name="type" value="boots">Boot<br>
  <input type="radio" name="type" value="waterproofBoots">Waterproof Boot<br>
  <input type="radio" name="type" value="flats">Flat<br>
  <input type="radio" name="type" value="heels">Heel<br>
  <input type="radio" name="type" value="sandals">Sandal<br>
  <input type="radio" name="type" value="dressShoes">Dress Shoe<br><br>
  <input type = "hidden" name ="category" value = "SHOE">
  


  <textarea name="description" size="20" placeholder="Add a brief item description..."></textarea><br>
<button type="submit">Add Item</button>
<button type="submit">Cancel</button>



</form>
</body>
</html>