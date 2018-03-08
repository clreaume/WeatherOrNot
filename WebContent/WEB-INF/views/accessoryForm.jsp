<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Accessories</h3>
<form action="addItem">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image" name ="imageURL"><br>
   
 
  <input type="radio" name="type" value="watch"> Watch<br>
  <input type="radio" name="type" value="bracelet"> Bracelet<br>
  <input type="radio" name="type" value="necklace"> Necklace<br>
  <input type="radio" name="type" value="earrings"> Earrings<br>
  <input type="radio" name="type" value="hat"> Hat<br>
  <input type="radio" name="type" value="scarf"> Scarf<br>
  <input type="radio" name="type" value="sunglasses"> Sunglasses<br>
  <input type="radio" name="type" value="gloves"> Gloves<br>
 <input type = "hidden" name ="category" value = "ACCESSORY">
  

  <textarea name="description" >Add a brief item description...</textarea>
  

 <button type="submit" >Add Item</button>
 <button type="button">Cancel</button>
 
 </form>
</body>
</html>