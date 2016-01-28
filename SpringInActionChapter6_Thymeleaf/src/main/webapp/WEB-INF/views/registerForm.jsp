<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" />
</head>
<body>
	<h1><s:message code="spittr.register.title"/></h1>

	<!-- 	Notice that the <form> tag doesn’t have an action parameter set. Because of that, -->
	<!-- when this form is submitted, it will be posted back to the same URL path that displayed -->
	<!-- it. That is, it will be posted back to /spitters/register. -->

	<%-- 	The <sf:form> tag renders an HTML <form> tag. But it also sets some context around --%>
	<!-- a model object designated in the commandName attribute. Properties on the model -->
	<!-- object will be referenced in the other form-binding tags you use. -->
	<!-- In the preceding code, you set commandName to spitter. Therefore, there must be -->
	<!-- an object in the model whose key is spitter, or else the form won’t be able to render -->
	<!-- (and you’ll get JSP errors). That means you need to make a small change to Spitter- -->
	<!-- Controller to ensure that a Spitter object is in the model under the spitter key -->
	<sf:form method="POST" commandName="spitter">
		<%-- 	its path is set to *. This is a wildcard selector that tells <sf:errors> --%>
		<!-- to render all errors for all properties -->
		<sf:errors path="*" element="div" cssClass="errors" />

		<!-- 		En caso de errores al completar los campos, se establecera la clase "error" definida en style.css para los elementos de springForms -->
		<sf:label path="firstName" cssErrorClass="error"><s:message code="spittr.register.firstName"/>:</sf:label>
		<sf:input path="firstName" cssErrorClass="error" />
		<br />

		<sf:label path="lastName" cssErrorClass="error"><s:message code="spittr.register.lastName"/>:</sf:label>
		<sf:input path="lastName" cssErrorClass="error" />
		<br />

		<sf:label path="email" cssErrorClass="error">Email:</sf:label>
		<sf:input path="email" cssErrorClass="error" />
		<br />

		<sf:label path="username" cssErrorClass="error"><s:message code="spittr.register.username"/>:</sf:label>
		<sf:input path="username" cssErrorClass="error" />
		<br />

		<sf:label path="password" cssErrorClass="error"><s:message code="spittr.register.password"/>: </sf:label>
		<sf:password path="password" cssErrorClass="error" />
		<br />
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>