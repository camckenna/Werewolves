<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vote</title>
</head>
<body>
	<form:form action="restart" method="POST">
	Game frequency (in minutes): <input type="text" name="frequency" />
	Scent Range (in kilometers): <input type="text" name="scentRange" />
	Kill Range (in kilometers): <input type="text" name="killRange" />
	<input type="submit" value="Submit" />      
	</form:form>
</body>
</html>