<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>Sign-Up Information</h2>
<form:form method="POST" action="/register">
   <table>
    <tr>
        <td><form:label path="email">Email</form:label></td>        
        <td><form:input path="email" /></td>
        <td><font color='red'><form:errors path='email' /></font></td>
    </tr>

    <tr>
        <td><form:label path="originalPassword">Password</form:label></td>
        <td><form:input path="originalPassword" /></td>
        <td><font color='red'><form:errors path='originalPassword' /></font></td>
    </tr>
	<tr>
        <td><form:label path="newPassword">New Password</form:label></td>
        <td><form:input path="newPassword" /></td>
        <td><font color='red'><form:errors path='newPassword' /></font></td>
    </tr>
    <tr>
        <td><form:label path="confirmNewPassword">Confirm New Password</form:label></td>
        <td><form:input path="confirmNewPassword" /></td>
        <td><font color='red'><form:errors path='confirmNewPassword' /></font></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>