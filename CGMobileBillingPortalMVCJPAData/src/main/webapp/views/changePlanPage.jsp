<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="form">
<form name="changePlan" action="changeMobilePlan" method="post">
Customer ID:
<input type="text" name="customerId">
Mobile Number:
<input type="text" name="mobileNumber">
New Plan ID:
<input type="text" name="planId">
<input type="submit" name="submit">
</form>
</div>
<div align="center">
<font color=green size=10>${success }</font>
</div>
</body>
</html>