<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fill Closet</title>
</head>
<body>
<h3> Welcome ${name}! Let's fill your closet!</h3>

<h4>Pick a category of clothing to begin.</h4>
<form action="getItemInputForm">
     <select name="itemOfClothing">
         <option value="top">Tops</option>
         <option value="sweater">Sweaters</option>
         <option value="dress">Dress</option>
         <option value="bottom">Bottoms</option>
         <option value="shoe">Shoes</option>
         <option value="outerwear">Outerwear</option>
         <option value="accessory">Accessories</option>
      </select><br><br><br> 
     <button type="submit">Select Category</button>
</body>
</html>