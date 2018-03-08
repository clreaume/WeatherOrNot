<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Tops</h3>
<form action="">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image"><br><br><br>
  <input type="radio" name="clothes" value="T-shirt">T-shirt<br>
  <input type="radio" name="clothes value="Blouse">Blouse<br>
  <input type="radio" name="clothes" value="Bottom-down">Bottom-down<br>
  <input type="radio" name="clothes value="Tank-top  ">Tank-top<br>
  <input type="radio" name="clothes value="Blouse">Collared<br>
  <input type="radio" name="clothes value="Blouse">Atheltic wear<br><br>
  <input type = "hidden" name ="category" value = "TOP">

  <textarea name="description" size="20">
  	Add a brief items description
  </textarea><br>
<button type="submit">Add Item</button>
<button type="submit">Cancel</button>
</form>
</body>
</html>