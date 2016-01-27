<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" />
</head>
<body>
	<h1><s:message code="spittr.profile.title" /> <c:out value="${spitter.username}" /></h1>

	<ul>
		<li><s:message code="spittr.register.firstName"/>: <c:out value="${spitter.firstName}" /></li>
		<li><s:message code="spittr.register.lastName"/>: <c:out value="${spitter.lastName}" /></li>
	</ul>
</body>
</html>