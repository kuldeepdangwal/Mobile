<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="form">
<form action="postpaidAccountOpen" method="post">
Customer ID:
<input type="text" name="customerId">
Plan ID:
<input type="text" name="planId">
<input type="submit" name="submit">
</form>
</div>
<div align="center">
Your system generated new Mobile Number: <font color=green>${mobileNumber }</font>
</div>
</body>
</html>