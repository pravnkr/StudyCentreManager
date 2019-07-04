<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ignou Badhega - StudyCentre Manager</title>
</head>
<body>

	<div>
		User:
		<security:authentication property="principal.username" />
		Role(s):
		<security:authentication property="principal.authorities" />
	</div>

	<br>
	<br>
	<hr>
	<div>Hi ${user.firstName}!</div>

	<br>
	<br>

	<div>
		Please select an Operation:
		<ul>
			
			<security:authorize access="hasRole('ADMIN')">
				
				<li><a
					href="${pageContext.request.contextPath}/coordinator/create-student">Save
						new student(s)</a></li>
				<li><a
					href="${pageContext.request.contextPath}/coordinator/update-student">Update
						existing student(s)</a></li>
				<li><a
					href="${pageContext.request.contextPath}/coordinator/delete-student">Delete
						student(s)</a></li>
				<li><a
				href="${pageContext.request.contextPath}/coordinator/register/showRegistrationForm">Add New User</a></li>
				
				<li><a
				href="${pageContext.request.contextPath}/coordinator/add-teacher">Add New Teacher</a></li>
				
				<li><a
				href="${pageContext.request.contextPath}/coordinator/generate-batch">Generate Batch</a></li>
					
			</security:authorize>
			
			<li><a
				href="${pageContext.request.contextPath}/staff/search-student">Search
					student</a></li>
		</ul>
	</div>
	
	<hr>
	
	<div>
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="submit" value="Logout" />
		</form:form>
	</div>

</body>
</html>