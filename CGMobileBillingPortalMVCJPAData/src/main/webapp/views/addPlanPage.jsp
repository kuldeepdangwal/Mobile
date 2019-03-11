<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Plan</title>
<style>
.error {
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
    <table>
	<form:form action="addPlanDetails" method="post"
            modelAttribute="plan">
            <tr>
                <td>freeInternetDataUsageUnits</td>
                <td><form:input path="freeInternetDataUsageUnits" size="30" /></td>
                <td><form:errors path="freeInternetDataUsageUnits" cssClass="error" />
            </tr>
            <tr>
                <td>freeLocalCalls</td>
                <td><form:input path="freeLocalCalls" size="30" /></td>
                <td><form:errors path="freeLocalCalls" cssClass="error" />
            </tr>
            <tr>
                <td>freeLocalSMS</td>
                <td><form:input path="freeLocalSMS" size="30" /></td>
                <td><form:errors path="freeLocalSMS" cssClass="error" />
            </tr>
            <tr>
                <td>freeStdCalls</td>
                <td><form:input path="freeStdCalls" size="30" /></td>
                <td><form:errors path="freeStdCalls" cssClass="error" />
            </tr>
            <tr>
                <td>freeStdSMS</td>
                <td><form:input path="freeStdSMS" size="30" /></td>
                <td><form:errors path="freeStdSMS" cssClass="error" />
            </tr>
            <tr>
                <td>internetDataUsageRate</td>
                <td><form:input path="internetDataUsageRate" size="30" /></td>
                <td><form:errors path="internetDataUsageRate" cssClass="error" />
            </tr>
            <tr>
                <td>localCallRate</td>
                <td><form:input path="localCallRate" size="30" /></td>
                <td><form:errors path="localCallRate" cssClass="error" />
            </tr>
            <tr>
                <td>localSMSRate</td>
                <td><form:input path="localSMSRate" size="30" /></td>
                <td><form:errors path="localSMSRate" cssClass="error" />
            </tr>
            <tr>
                <td>monthlyRental</td>
                <td><form:input path="monthlyRental" size="30" /></td>
                <td><form:errors path="monthlyRental" cssClass="error" />
            </tr>
            <tr>
                <td>planCircle</td>
                <td><form:input path="planCircle" size="30" /></td>
                <td><form:errors path="planCircle" cssClass="error" />
            </tr>
             <tr>
                <td>planName</td>
                <td><form:input path="planName" size="30" /></td>
                <td><form:errors path="planName" cssClass="error" />
            </tr>
             <tr>
                <td>stdCallRate</td>
                <td><form:input path="stdCallRate" size="30" /></td>
                <td><form:errors path="stdCallRate" cssClass="error" />
            </tr>
             <tr>
                <td>stdSMSRate</td>
                <td><form:input path="stdSMSRate" size="30" /></td>
                <td><form:errors path="stdSMSRate" cssClass="error" />
            </tr>
            <tr>
                <td><input type="submit" value="Submit" />
            </tr>
        </form:form>
    </table>
    <div>
    	Plan Added : ${plan.planID}
    </div>
</body>
</html>