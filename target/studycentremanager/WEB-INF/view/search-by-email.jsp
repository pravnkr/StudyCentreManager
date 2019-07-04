<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	<span> 
		<a href="${pageContext.request.contextPath}/staff/search-student">Back</a>
	</span>

	<h2>IgnouBadhega - Search Student By Enrollment No</h2>

	<form:form
		action="${pageContext.request.contextPath}/staff/search-student/by-email/fetch"
		modelAttribute="studentWithEmail">

		<label>Please Enter the Email Id of student:</label>
		<br>
		<form:input path="email" />
		<input type="submit" value="Fetch A Record">
		<br>
		<form:errors path="email"></form:errors>

	</form:form>

	<c:if test="${recordNotFetchedByEmail}">
		<i>Student does not exist with Email Id: </i>
		<em>${searchCriteriaAttributeVal}.</em>
	</c:if>

	<c:if test="${recordFetchedByEmail}">

		<form:form
			action=""
			modelAttribute="fetchedStudentWithEmail">

			<table>

				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Enrollment No</th>
					<th>Email</th>
					<th>Mob</th>
					<th>Programme</th>
					<th>Semester</th>
				</tr>

				<tr>
					<td><form:input path="firstname" disabled="true" /><br>
					<form:errors path="firstname" /></td>
					<td><form:input path="lastname" disabled="true" /><br>
					<form:errors path="lastname" /></td>
					<td><form:input path="enrollNo" disabled="true" /><br>
					<form:errors path="enrollNo" /></td>
					<td><form:input path="email" disabled="true" /><br>
					<form:errors path="email" /></td>
					<td><form:input path="mob" disabled="true" /><br>
					<form:errors path="mob" /></td>
					<td><form:select path="programme" disabled="true">
							<form:option value="BCA">BCA</form:option>
							<form:option value="MCA">MCA</form:option>
						</form:select><br>
					<form:errors path="programme" /></td>
					<td><form:select path="semester" disabled="true">
							<form:option value="1">1st Semester</form:option>
							<form:option value="2">2nd Semester</form:option>
							<form:option value="3">3rd Semester</form:option>
							<form:option value="4">4th Semester</form:option>
							<form:option value="5">5th Semester</form:option>
							<form:option value="6">6th Semester</form:option>
						</form:select><br>
					<form:errors path="semester" /></td>
				</tr>

			</table>

		</form:form>

	</c:if>
	
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