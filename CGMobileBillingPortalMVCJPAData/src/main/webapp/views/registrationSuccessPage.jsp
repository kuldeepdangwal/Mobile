<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div align="center">
<h2>Welcome ${customer.firstName}&nbsp${customer.lastName}</h2>
<h2>Registration done successfully</h2>
<h2>Customer ID: ${customer.customerID}</h2>
<table>
<tr>
<th colspan="2">Telephone</th>
</tr>
<tr>
<th>First Name</th>
<th>LastName</th>
</table>
</div>
</body>
</html>