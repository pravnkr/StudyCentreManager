<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Search Student</title>
</head>
<body>

	<span> 
		<a href="${pageContext.request.contextPath}">Dashboard</a>
	</span>

	<h2>IgnouBadhega - Search Student</h2>

	<h3>Please select search criteria</h3>
	<ul>
	
		<li><a href="${pageContext.request.contextPath}/staff/search-student/by-enroll">By Enrollment No</a></li>
		<li><a href="${pageContext.request.contextPath}/staff/search-student/by-mob">By Mobile No</a></li>
		<li><a href="${pageContext.request.contextPath}/staff/search-student/by-email">By Email Id</a></li>
	
	</ul>

	<br>
	<hr>

	<span> 
		<!-- Add a logout button --> 
		<form:form
			action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
	</span>

</body>
</html>