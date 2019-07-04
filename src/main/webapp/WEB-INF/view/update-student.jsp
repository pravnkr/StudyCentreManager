<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Update an existing Student</title>
</head>
<body>

	<span> 
		<a href="${pageContext.request.contextPath}">Dashboard</a>
	</span>

	<h2>IgnouBadhega - Update an existing Student</h2>

	<h3>How would you like to tell me about the student you need to update ?</h3>
	<ul>
	
		<li><a href="${pageContext.request.contextPath}/coordinator/update-student/by-enroll">By Enrollment No</a></li>
		<li><a href="${pageContext.request.contextPath}/coordinator/update-student/by-mob">By Mobile No</a></li>
		<li><a href="${pageContext.request.contextPath}/coordinator/update-student/by-email">By Email Id</a></li>
	
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