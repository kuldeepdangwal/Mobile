<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
<style>
.error {
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
    <table>
        <form:form action="registrationCustomer" method="post"
            modelAttribute="customer">
            <tr>
                <td>First Name</td>
                <td><form:input path="firstName" size="30" /></td>
                <td><form:errors path="firstName" cssClass="error" />
            </tr>
            <tr>
                <td>Last Name</td>
                <td><form:input path="lastName" size="30" /></td>
                <td><form:errors path="lastName" cssClass="error" />
            </tr>
            <tr>
                <td>Date Of Birth</td>
                <td><form:input path="dateOfBirth" size="30" /></td>
                <td><form:errors path="dateOfBirth" cssClass="error" />
            </tr>
            <tr>
                <td>EmailId</td>
                <td><form:input path="emailID" size="30" /></td>
                <td><form:errors path="emailID" cssClass="error" />
            </tr>
            <tr>
                <td>Billing City</td>
                <td><form:input path="billingAddress.city" size="30" /></td>
                <td><form:errors path="billingAddress.city" cssClass="error" />
            </tr>
            <tr>
                <td>Billing State</td>
                <td><form:input path="billingAddress.state" size="30" /></td>
                <td><form:errors path="billingAddress.state" cssClass="error" />
            </tr>
            <tr>
                <td>Billing PinCode</td>
                <td><form:input path="billingAddress.pinCode" size="30" /></td>
                <td><form:errors path="billingAddress.pinCode" cssClass="error" />
            </tr>
            <tr>
                <td>Home City</td>
                <td><form:input path="homeAddress.city" size="30" /></td>
                <td><form:errors path="homeAddress.city" cssClass="error" />
            </tr>
            <tr>
                <td>Home State</td>
                <td><form:input path="homeAddress.state" size="30" /></td>
                <td><form:errors path="homeAddress.state" cssClass="error" />
            </tr>
            <tr>
                <td>Home PinCode:</td>
                <td><form:input path="homeAddress.pinCode" size="30" /></td>
                <td><form:errors path="homeAddress.pinCode" cssClass="error" />
            </tr>
            <tr>
                <td><input type="submit" value="Submit" />
            </tr>
        </form:form>
    </table>
</body>
</html>