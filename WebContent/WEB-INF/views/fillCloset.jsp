<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css"
	media="screen">
	
<title>Fill Closet</title>
</head>
<body>

<h1>${name}, let's add to your closet:</h1>

<div class = "addItemDiv">
<h4>Choose the category of the item to add:</h4>

<form action="getItemInputForm" class = "blockButton">
<br> 
     <select name="itemOfClothing">
         <option value="top">Top</option>
         <option value="sweater">Sweater</option>
         <option value="dress">Dress</option>
         <option value="bottom">Bottom</option>
         <option value="shoe">Shoes</option>
         <option value="outerwear">Outerwear</option>
         <option value="accessory">Accessory</option>
      </select><br><br>
     <input type="submit" value = "Select category"><br>
  </form>
  
  </div>
  
  <div class = "inlineButtonDiv">
  <form action="viewCloset" class = "inlineButton">
		<input type="submit" value="View closet">
	</form>
	<form action="viewHamp" class = "inlineButton">
		<input type="submit" value="View Hamper">
	</form>
	</div>
</body>
</html>