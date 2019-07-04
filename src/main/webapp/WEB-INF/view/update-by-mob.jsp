<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	<span> 
		<a href="${pageContext.request.contextPath}/coordinator/update-student">Back</a>
	</span>

	<h2>IgnouBadhega - Update an existing Student by Mobile No</h2>
	
	<form:form
		action="${pageContext.request.contextPath}/coordinator/update-student/by-mob/fetch"
		modelAttribute="studentWithMob">

		<label>Please Enter the Mobile no of student:</label>
		<br>
		<form:input path="mob" />
		<input type="submit" value="Fetch A Record">
		<br><form:errors path="mob" />
	</form:form>
	
	
	<c:if test="${recordNotFetchedByMob}">
		<i>Student does not exist with Mob No: </i> <em>${updateCriteriaAttributeVal}.</em>
	</c:if>
	
	<c:if test="${recordFetchedByMob || inputError}">
	
		<form:form action="${pageContext.request.contextPath}/coordinator/update-student/by-mob/save" modelAttribute="fetchedStudentWithMob">
			
			<input type="hidden" name="id" value="${id}" />
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
					<td><form:input path="firstname" /><br><form:errors path = "firstname" /></td>
					<td><form:input path="lastname"/><br><form:errors path = "lastname" /></td>
					<td><form:input path="enrollNo"/><br><form:errors path = "enrollNo" /></td>
					<td><form:input path="email"/><br><form:errors path = "email" /></td>
					<td><form:input path="mob"/><br><form:errors path = "mob" /></td>
					<td>
						<form:select path="programme">
							<form:option value="BCA">BCA</form:option>
							<form:option value="MCA">MCA</form:option>
						</form:select><br><form:errors path = "programme" />
					</td>
					<td>
						<form:select path="semester">
							<form:option value="1">1st Semester</form:option>
							<form:option value="2">2nd Semester</form:option>
							<form:option value="3">3rd Semester</form:option>
							<form:option value="4">4th Semester</form:option>
							<form:option value="5">5th Semester</form:option>
							<form:option value="6">6th Semester</form:option>
						</form:select><br><form:errors path = "semester" />
					</td>
				</tr>
				
				<tr>
					<td><input type="submit" value="update" /></td>
				</tr>
							
			</table>
			
		</form:form>
	
	</c:if>
	
	<c:if test="${updateError != null}">

		<i>Their was a conflict arised for the update request Because
			their is allready another student persistent in records having either
			the same Enrollment no/Email/Mob.</i>
		<i>For brevity, the record allready persistent is below: </i>

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
				<td><c:out value="${existingStudent.firstname}" /></td>
				<td><c:out value="${existingStudent.lastname}" /></td>
				<td><c:out value="${existingStudent.enrollNo}" /></td>
				<td><c:out value="${existingStudent.email}" /></td>
				<td><c:out value="${existingStudent.mob}" /></td>
				<td><c:out value="${existingStudent.programme}" /></td>
				<td><c:out value="${existingStudent.semester}" /></td>
			</tr>

		</table>

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