<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" />
</head>
<body>
	<h1><c:out value="${spitter.username}'s profile"></c:out></h1>

	<ul>
		<li><c:out value="First name: ${spitter.firstName}"></c:out></li>
		<li><c:out value="Last name: ${spitter.lastName}"></c:out></li>
	</ul>
</body>
</html>