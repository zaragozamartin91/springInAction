<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>spittles</title>
</head>

<body>
	<h1><c:out value="Spittle #${spittleId}"></c:out></h1>
	<ul>
		<li><c:out value="Date: ${spittle.time}"></c:out></li>
		<li><c:out value="Msg: ${spittle.message}"></c:out></li>
	</ul>
</body>
</html>