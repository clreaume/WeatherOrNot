<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Sweaters</h3>
<form action="addItem">
  <input type="text" placeholder="File Upload">
  <input type="file" name="imageURL" accept="image"><br>
  <fieldset>
  <input type="radio" name="type" value="hoody"> Hoody<br>
  <input type="radio" name="type" value="sweatshirt"> Sweatshirt<br>
  <input type="radio" name="type" value="crewneck"> Crewneck<br>
  <input type="radio" name="type" value="zipUp"> Zip-Up<br>
  <input type="radio" name="type" value="cardigan"> Cardigan<br>
  <input type = "hidden" name ="category" value = "SWEATER">
 
  </fieldset>

  <textarea name="description">Add a brief item description...</textarea>
  
 <button >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>
</form>
</body>
</html>