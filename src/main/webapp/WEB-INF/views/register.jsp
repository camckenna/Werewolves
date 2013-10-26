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
        <td><form:label path="firstName">First Name</form:label></td>
        <td><form:input path="firstName" /></td>
        <td><font color='red'><form:errors path='firstName' /></font></td>
        
    </tr>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" /></td>
        <td><font color='red'><form:errors path='lastName' /></font></td>
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
</body>
</html>