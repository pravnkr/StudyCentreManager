<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Delete Student</title>
</head>
<body>

	<span> 
		<a href="${pageContext.request.contextPath}">Dashboard</a>
	</span>

	<h2>IgnouBadhega - Delete Student</h2>

	<h3>How would you like to tell me about the student you need to delete?</h3>
	<ul>
	
		<li><a href="${pageContext.request.contextPath}/coordinator/delete-student/by-enroll">By Enrollment No</a></li>
		<li><a href="${pageContext.request.contextPath}/coordinator/delete-student/by-mob">By Mobile No</a></li>
		<li><a href="${pageContext.request.contextPath}/coordinator/delete-student/by-email">By Email Id</a></li>
	
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