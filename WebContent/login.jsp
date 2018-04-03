<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
<form action="login" method="post">
<table>
<tr><td>${message}</td><td></td></tr>
<tr><td>${successMessage}</td><td></td></tr>
<tr><td> UserName : </td><td><input type="text" name="username"></td><tr>
<tr><td> Password : </td><td><input type="password" name="password"></td><tr>
<tr><td><input type="submit" name="submit" value="Login"></td><td><a href="register.jsp">Registration</a></td></tr>
</table>
</form>
</body>
</html>