<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register page</title>
</head>
<body>
<form action="login" method="post">
<table>
<tr><td>${message}</td><tr>
<tr><td>${successMessage}</td><tr>
<tr><td>${failMessage}</td><tr>
<tr><td> UserName : </td><td><input type="text" name="username"></td><tr>
<tr><td> First Name : </td><td><input type="text" name="fname"></td><tr>
<tr><td> Last Name : </td><td><input type="text" name="lname"></td><tr>
<tr><td> Password : </td><td><input type="password" name="password"></td><tr>
<tr><td> Confirm password : </td><td><input type="password" name="password2"></td><tr>
<tr><td><input type="submit" name="submit" value="Register"></td><td><a href="login.jsp">Registration</a></td></tr>
</table>
</form>
</body>
</html>