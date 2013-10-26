<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>Delete Account</h2>
<form:form method="POST" action="/delete">
Password: <input type="text" name="password" />
	<input type="submit" value="Submit" />
</form:form>
</body>
</html>