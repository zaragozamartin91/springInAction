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
	<h1>Recent Spittles</h1>

	<c:set var="sid" value="0"></c:set>
	<c:forEach items="${spittleList}" var="spittle">
		<c:set var="sid" value="${sid + 1}"></c:set>
		<ul>
			<li><c:out value="${sid}->"></c:out> <c:out value="${spittle.time}"></c:out></li>
			<c:if test="${not empty spittle.message}">
				<c:out value="message: ${spittle.message}"></c:out>
			</c:if>
		</ul>
	</c:forEach>
</body>
</html>