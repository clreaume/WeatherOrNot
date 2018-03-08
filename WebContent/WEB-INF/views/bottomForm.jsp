<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Bottoms</h3>
<form action ="addItem">

  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image" name ="imageURL"><br><br><br>

  <input type="radio" name="type" value="pants" > Pants<br>
  <input type="radio" name="type" value="jeans"> Jeans<br>
  <input type="radio" name="type" value="shorts"> Shorts<br>
  <input type="radio" name="type" value="skirt"> Skirt<br>
  <input type="radio" name="type" value="capris"> Capris<br>
  <input type="radio" name="type" value="leggings"> Leggings<br>
  <input type="radio" name="type" value="athletic"> Athletic Bottoms<br>
  <input type = "hidden" name ="category" value = "BOTTOM">



  <textarea name="description">Add a brief item description...</textarea>
  


 <button type="button AddItem-button" id="button1" >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>
</form>
</body>
</html>