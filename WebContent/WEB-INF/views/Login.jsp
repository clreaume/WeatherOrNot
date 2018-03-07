<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<h1>Welcome To WeatherOrNot!</h1>
<h3>When you're getting ready always listen to your Mother...</h3>
<h3>...Mother Nature</h3>

<form action="fashionCast" method="POST"></form>


    
   <form class="pure-form"> 
   <fieldset>
        

        <input type="text" id="email" placeholder="janedoe@abc.com"> <br>
        <input type="email" placeholder="Confirm email" id="confirm_email" required>
        <input type="password" placeholder="Password" id="password" required>
        <input type="password" placeholder="Confirm Password" id="confirm_password" required>
        
        <button type="submit" class="pure-button pure-button-primary">Confirm</button>
        
       
    </fieldset>
    
</form>

<h1>New To WeatherOrNot?</h1>
<form action="getsignup" method="POST"></form>
<form>
    <fieldset>
        

        
        <button type="submit" class="pure-button pure-button-primary">Sign Up</button>
        
   </fieldset> 
 </form>  





<script>
var password = document.getElementById("password")
, confirm_password = document.getElementById("confirm_password");

function validatePassword(){
if(password.value != confirm_password.value) {
  confirm_password.setCustomValidity("Passwords Don't Match");
} else {
  confirm_password.setCustomValidity('');
}
}

email.onchange = validateEmail;
confirm_password.onkeyup = validatePassword;

var password = document.getElementById("email")
, confirm_password = document.getElementById("confirm_email");

function validateEmail(){
if(email.value != confirm_email.value) {
  confirm_email.setCustomValidity("Email Don't Match");
} else {
  confirm_email.setCustomValidity('');
}
}

email.onchange = validateEmail;
confirm_email.onkeyup = validateEmail;

</script>
<button type="button" onclick="validatePassword();">Submit</button>

<!--
<script>script type="text/javascript" src="/js/templates-b975a87a.js>"</script>
<script>script type="text/javascript" src="https://connect.facebook.net/en us/sdk.js"></script>
<script>script type="text/javascript" src="/js/app-8cdc408a.js>"</script>
<script>script type="text/javascript">...</script>
	<div id>="fb-root"class=" fb_reset"</div>
<script id>src="https://log.pintrest.com/?quid=MxQigcLLnEF0&tv=2017100302&type=pidget&2Fclosetspace.com%2Fwomen%2Fshop&callback=PIN 1520264471804.f.callback[0]"></script>
-->



</body>
</html>