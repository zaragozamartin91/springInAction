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
	
	<br/>
	<br/>
	<br/>

	<!-- 	general-purpose escaping tag. It renders any content -->
	<!-- nested in its body, escaping as necessary. -->
	<!-- For example, suppose you want to display a snippet of HTML code on a page. In -->
	<!-- order for it to be displayed properly, the < and > characters need to be replaced with -->
	<!-- &lt; and &gt; or the browser will interpret the HTML as any other HTML in the page. -->
	<s:escapeBody htmlEscape="true">
		<h1>This is some escaped html</h1>
		<p>It's quite good to show some code on your site...</p>
	</s:escapeBody>
</body>
</html>