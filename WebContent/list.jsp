<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Comments</title>
</head>
<body>
<form action="LoginController" method="post">
<table>
<tr><td>${messageList}</td><td></td></tr>
<tr><td><input type="submit" name="submit" value="Log Out"></td><td></td></tr>
<tr><td><input type="submit" name="submit" value="Insert More Comments"></td><td></td></tr>
</table>
</form>
</body>
</html>