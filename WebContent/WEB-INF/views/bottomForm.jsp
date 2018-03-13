<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.css" media="screen">
<title>Bottom</title>
</head>
<body>
<h3>Bottom</h3>
<p>Upload a photo of the item.</p>
<form action ="addItem" method="post" enctype="multipart/form-data">

  <input type="file" accept="image" name ="imageURL"><br>
  <p>Choose the type of bottom being added to the closet:</p>
  <input type="radio" name="type" value="pants" > Pants<br>
  <input type="radio" name="type" value="jeans"> Jeans<br>
  <input type="radio" name="type" value="shorts"> Shorts<br>
  <input type="radio" name="type" value="skirt"> Skirt<br>
  <input type="radio" name="type" value="capris"> Capris<br>
  <input type="radio" name="type" value="leggings"> Leggings<br>
  <input type="radio" name="type" value="athletic"> Athletic Bottoms<br>
  <input type = "hidden" name ="category" value = "BOTTOM"><br>

  <textarea name="description" placeholder="Add a brief item description..."></textarea><br>
  
 <button type="button AddItem-button" id="button1" >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>
</form>
</body>
</html>