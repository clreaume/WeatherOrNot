<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Outerwear</title>
</head>
<body>
<h3>Outerwear</h3>
<p>Upload a photo of the item.</p>
<form action="addItem" method="post" enctype="multipart/form-data">
  <input type="text" placeholder="File Upload">
  <input type="file" accept="image" name="imageURL"><br>
  <p>Choose the type of accessory being added to the closet:</p>

  <input type="radio" name="type" value="jacket"> Jacket<br>
  <input type="radio" name="type" value="fleeceJacket"> Fleece Jacket<br>
  <input type="radio" name="type" value="rainCoat"> Rain Coat<br>
  <input type="radio" name="type" value="parka"> Parka<br>
  <input type="radio" name="type" value="peaCoat"> Pea Coat<br>
  <input type="radio" name="type" value="leatherJacket"> Leather Jacket<br>
  <input type="radio" name="type" value="winterCoat"> Winter Coat<br>
  <input type="radio" name="type" value="furCoat"> Fur Coat<br>
  <input type = "hidden" name ="category" value = "OUTERWEAR">

  <textarea name="description" placeholder="Add a brief item description..."></textarea><br>
   <button type="button AddItem-button" id="button1" >Add Item</button>
   <button type="button Cancel-button" id="button2">Cancel</button>
 </form>
</body>
</html>