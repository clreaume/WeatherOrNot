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
<form action="">
  <input type="text" placeholder="File Upload">
  <input type="file" name="pic" accept="image"><br>
  
  </form>
 <form>
  <fieldset>
  <input type="radio" name="accessory" value="Watches"> Watches<br>
  <input type="radio" name="accessory" value="Bracelets"> Bracelets<br>
  <input type="radio" name="accessory" value="Necklace"> Necklace<br>
  <input type="radio" name="accessory" value="Ear Rings"> Ear Rings<br>
  <input type="radio" name="accessory" value="Hats"> Hats<br>
  <input type="radio" name="accessory" value="Scarf"> Scarf<br>
  <input type="radio" name="accessory" value="Sunglasses"> Sunglasses<br>
  <input type="radio" name="accessory" value="Gloves"> Gloves<br>
  
</fieldset>
</form>

<form action="/action_page.php" id="usrform">

  <textarea name="comment" form="usrform">Add a brief item description...</textarea>
  
</form>


 <button type="button AddItem-button" id="button1" >Add Item</button>
 <button type="button Cancel-button" id="button2">Cancel</button>
</body>
</html>