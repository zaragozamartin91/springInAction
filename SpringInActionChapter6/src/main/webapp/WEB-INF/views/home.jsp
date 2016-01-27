<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
</head>
<body>
	<%-- 	<s:message> will render the text available from a message source where --%>
	<!-- the key is spittr.welcome. 
	Actualmente esta propiedad se encuentra en los archivos messages*.properties -->
	<h1><s:message code="spittr.welcome" /></h1>

	<%-- 	you can have <s:url> construct the URL and assign it to a variable to --%>
	<!-- be used later in the template -->
	<s:url value="/spittles" var="spittlesUrl">
		<s:param name="max" value="60" />
		<s:param name="count" value="20" />
	</s:url>
	<a href="${spittlesUrl}">Spittles</a> |
	<a href="<c:url value="/spitter/register" />"><s:message code="spittr.register" /></a>
</body>
</html>