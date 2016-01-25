<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" />
</head>
<body>
	<h1>Register</h1>
	
<!-- 	Notice that the <form> tag doesn’t have an action parameter set. Because of that, -->
<!-- when this form is submitted, it will be posted back to the same URL path that displayed -->
<!-- it. That is, it will be posted back to /spitters/register. -->
	<form action="POST">
		First name: <input type="text" name="firstName"/><br/>
		Last name: <input type="text" name="lastName"/><br/>
		Username: <input type="text" name="username"/><br/>
		Password: <input type="password" name="firstName"/><br/>
		<input type="submit" value="Register"/>
	</form>
</body>
</html>