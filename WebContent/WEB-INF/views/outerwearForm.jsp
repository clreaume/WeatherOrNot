<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Outerwear</h3>
<form action="addItem">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image" name="imageURL"><br>
  
  <fieldset>
  <input type="radio" name="type" value="jacket"> Jacket<br>
  <input type="radio" name="type" value="fleeceJacket"> Fleece Jacket<br>
  <input type="radio" name="type" value="rainCoat"> Rain Coat<br>
  <input type="radio" name="type" value="parka"> Parka Jackets<br>
  <input type="radio" name="type" value="peaCoat"> Pea Coat<br>
  <input type="radio" name="type" value="leatherJacket"> Leather Jacket<br>
  <input type="radio" name="type" value="winterCoat"> Winter Coat<br>
  <input type="radio" name="type" value="furCoat"> Fur Coat<br>
  <input type = "hidden" name ="category" value = "OUTERWEAR">
  </fieldset>
  <textarea name="description" >Add a brief item description...</textarea>
   <button type="button AddItem-button" id="button1" >Add Item</button>
   <button type="button Cancel-button" id="button2">Cancel</button>
 </form>
</body>
</html>