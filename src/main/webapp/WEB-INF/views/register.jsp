<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Register</title>
    
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/styles/welcome.css'/>">
</head>
<body>

<div class="center">
<h2>Sign-Up Information</h2>
<form:form method="POST" modelAttribute="TempUser" action="/register">
   <table>
    <tr>
        <td><form:label path="email">Email</form:label></td>        
        <td><form:input path="email" /></td>
        <td><font color='red'><form:errors path='email' /></font></td>
    </tr>
    <tr>
        <td><form:label path="username">Username</form:label></td>
        <td><form:input path="username" /></td>
        <td><font color='red'><form:errors path='username' /></font></td>
    </tr>
        <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" /></td>
        <td><font color='red'><form:errors path='password' /></font></td>
    </tr>
    <tr>
        <td><form:label path="confirmPassword">Confirm Password</form:label></td>
        <td><form:input path="confirmPassword" /></td>
        <td><font color='red'><form:errors path='confirmPassword' /></font></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
</body>
</html>