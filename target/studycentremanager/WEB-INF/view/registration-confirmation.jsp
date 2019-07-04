<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
<title>Registration Confirmation</title>
</head>

<body>

	<h2>User registered successfully!</h2>

	<hr>

	<a href="${pageContext.request.contextPath}/showMyLoginPage">Login
		with new user</a>
	<a href="${pageContext.request.contextPath}">Dashboard</a>

	<br>
	<hr>

	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>

</body>

</html>