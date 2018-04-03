<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
<form action="login" method="post">
<table>
<tr><td>${message}</td><td></td></tr>
<tr><td>Leave your comment of the class here: </td><td><input type="text" name="comment"></td><td><input type="submit" name="submit" value="Insert Comment"></td><td></td></tr>
<tr><td>${messageAdded}</td><td></td></tr>
<tr><td><input type="submit" name="submit" value="Show Comments"></td><td></td></tr>
<tr><td><input type="submit" name="submit" value="Log Out"></td><td></td></tr>
</table>
</form>
</body>
</html>