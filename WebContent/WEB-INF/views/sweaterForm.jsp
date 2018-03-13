<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Sweater</title>
</head>
<body>
<h3>Sweater</h3>
<p>Upload a photo of the item.</p>
<form action="addItem" method="post" enctype="multipart/form-data">
  <input type="text" placeholder="File Upload">
  <input type="file" name="imageURL" accept="image"><br>

  <p>Choose the type of sweater being added to the closet:</p>
  <input type="radio" name="type" value="hoody"> Hoody<br>
  <input type="radio" name="type" value="crewneck"> Crewneck<br>
  <input type="radio" name="type" value="zipUp"> Zip-Up<br>
  <input type="radio" name="type" value="cardigan"> Cardigan<br>
  <input type = "hidden" name ="category" value = "SWEATER">
 

  <textarea name="description" placeholder="Add a brief item description..."></textarea><br>
  
 <button >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>
</form>
</body>
</html>