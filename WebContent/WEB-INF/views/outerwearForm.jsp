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
<form action="">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image"><br>
  
  </form>
 <form>
  <fieldset>
  <input type="radio" name="outerwear" value="Jacket"> Jacket<br>
  <input type="radio" name="outerwear" value="Fleece Jacket"> Fleece Jacket<br>
  <input type="radio" name="outerwear" value="Rain Coat"> Rain Coat<br>
  <input type="radio" name="outerwear" value="Parka Jacket"> Parka Jackets<br>
  <input type="radio" name="outerwear" value="Pea Coat"> Pea Coat<br>
  <input type="radio" name="outerwear" value="Leather Jacket"> Leather Jacket<br>
  <input type="radio" name="outerwear" value="Winter Coat"> Winter Coat<br>
  <input type="radio" name="outerwear" value="Fur Coat"> Fur Coat<br>
  <input type = "hidden" name ="category" value = "OUTERWEAR">
  
</fieldset>
</form>

<form action="/action_page.php" id="usrform">

  <textarea name="comment" form="usrform">Add a brief item description...</textarea>
  
</form>


 <button type="button AddItem-button" id="button1" >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>

</body>
</html>