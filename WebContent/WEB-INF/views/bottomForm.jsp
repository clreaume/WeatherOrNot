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
<form>
  <fieldset>
  <input type="radio" name="bottom" value="Pants" > Pants<br>
  <input type="radio" name="bottom" value="Jeans"> Jeans<br>
  <input type="radio" name="bottom" value="Shorts"> Shorts<br>
  <input type="radio" name="bottom" value="Skirt"> Skirt<br>
  <input type="radio" name="bottom" value="Capris"> Capris<br>
  <input type="radio" name="bottom" value="Leggings"> Leggings<br>
  <input type="radio" name="bottom" value="Athletic"> Athletic Bottoms<br>
  <input type = "hidden" name ="category" value = "BOTTOM">
</fieldset>
</form>

<form action="/action_page.php" id="usrform">

  <textarea name="comment" form="usrform">Add a brief item description...</textarea>
  
</form>


 <button type="button AddItem-button" id="button1" >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>

</body>
</html>