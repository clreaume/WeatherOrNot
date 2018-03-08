<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dress Form</title>
</head>
<body>
<h3>Dress</h3>
<form action="">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image"><br><br>
  <input type="text" placeholder="Give the Item name">
  <button type="submit">Ok</button><br><br>
  <input type="radio" name="clothes" value="Max">Max<br>
  <input type="radio" name="clothes value="Fancy">Fancy<br>
  <input type="radio" name="clothes" value="Party">Party<br>
  <input type="radio" name="clothes value="Mindy">Mindy<br>
  <input type="radio" name="clothes value="Flare">Flare<br>
  <input type="radio" name="clothes value="Gown">Gown<br><br>
  <input type = "hidden" name ="category" value = "DRESS">
  

<button type="submit">Add Item</button>
<button type="submit">Cancel</button>



</form>
</body>
</html>