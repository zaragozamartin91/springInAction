<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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