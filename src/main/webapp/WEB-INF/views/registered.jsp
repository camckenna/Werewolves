<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/login-header.jsp"%>
<html>
<head>
	<title>Werewolves!</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/styles/welcome.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/styles/buttons.css'/>">
</head>
<body class="center">

<h3 >Great! You've been added to the database. </h3>
<a class="login" href="<c:url value="/home" />"> Home </a>
</body>
</html>
