<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Save new student</title>
<style>
table {
	border: 1px solid black;
}

th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>


	<span> 
		<a href="${pageContext.request.contextPath}">Dashboard</a>
	</span>
	
	<h3>Save A New Student</h3>
	<form:form
		action="${pageContext.request.contextPath}/coordinator/create-student/save"
		modelAttribute="studentsWrapper">

		<c:if test="${partialSaveSuccess != null}">
			<i>${partialSaveSuccess}</i>
			<br>
		</c:if>

		<c:if test="${saveSuccessfull != null}">
			<i>${saveSuccessfull}</i>
			<br>
		</c:if>

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
			<c:forEach items="${studentsWrapper.list}" varStatus="status">
				<tr>
					<td><form:input path="list[${status.index}].firstname" /><br>
						<form:errors path="list[${status.index}].firstname" /></td>
					<td><form:input path="list[${status.index}].lastname" /><br>
						<form:errors path="list[${status.index}].lastname" /></td>
					<td><form:input path="list[${status.index}].enrollNo" /><br>
						<form:errors path="list[${status.index}].enrollNo" /></td>
					<td><form:input path="list[${status.index}].email" /><br>
						<form:errors path="list[${status.index}].email" /></td>
					<td><form:input path="list[${status.index}].mob" /><br>
						<form:errors path="list[${status.index}].mob" /></td>
					<td><form:select path="list[${status.index}].programme">
							<form:option value="BCA">BCA</form:option>
							<form:option value="MCA">MCA</form:option>
						</form:select><br> <form:errors path="list[${status.index}].programme" /></td>
					<td><form:select path="list[${status.index}].semester">
							<form:option value="1">1st Semester</form:option>
							<form:option value="2">2nd Semester</form:option>
							<form:option value="3">3rd Semester</form:option>
							<form:option value="4">4th Semester</form:option>
							<form:option value="5">5th Semester</form:option>
							<form:option value="6">6th Semester</form:option>
						</form:select><br> <form:errors path="list[${status.index}].semester" /></td>
				</tr>
			</c:forEach>

			<tr>
				<td><input type="submit" name="add-row" value="+" /></td>
				<td><input type="submit" name="save" value="save" /></td>
			</tr>

		</table>

	</form:form>

	<c:if test="${dupEnrollSaveError != null}">

		<i>${dupEnrollSaveError}</i>
		<br>

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
			<c:forEach var="student" items="${studentsWithDupEnroll}">
				<tr>
					<td><c:out value="${student.firstname}" /></td>
					<td><c:out value="${student.lastname}" /></td>
					<td><c:out value="${student.enrollNo}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.mob}" /></td>
					<td><c:out value="${student.programme}" /></td>
					<td><c:out value="${student.semester}" /></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

	<c:if test="${dupEmailSaveError != null}">

		<i>${dupEmailSaveError}</i>
		<br>

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
			<c:forEach var="student" items="${studentsWithDupEmail}">
				<tr>
					<td><c:out value="${student.firstname}" /></td>
					<td><c:out value="${student.lastname}" /></td>
					<td><c:out value="${student.enrollNo}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.mob}" /></td>
					<td><c:out value="${student.programme}" /></td>
					<td><c:out value="${student.semester}" /></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

	<c:if test="${dupMobSaveError != null}">

		<i>${dupMobSaveError}</i>
		<br>

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
			<c:forEach var="student" items="${studentsWithDupMob}">
				<tr>
					<td><c:out value="${student.firstname}" /></td>
					<td><c:out value="${student.lastname}" /></td>
					<td><c:out value="${student.enrollNo}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.mob}" /></td>
					<td><c:out value="${student.programme}" /></td>
					<td><c:out value="${student.semester}" /></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

	<c:if test="${IncorrSaveError != null}">

		<i>${IncorrSaveError}</i>
		<br>

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
			<c:forEach var="student" items="${studentsWithIncorrProg}">
				<tr>
					<td><c:out value="${student.firstname}" /></td>
					<td><c:out value="${student.lastname}" /></td>
					<td><c:out value="${student.enrollNo}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.mob}" /></td>
					<td><c:out value="${student.programme}" /></td>
					<td><c:out value="${student.semester}" /></td>
				</tr>
			</c:forEach>

		</table>

	</c:if>

	<br>
	<hr>

	<span> <!-- Add a logout button --> <form:form
			action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
	</span>

</body>
</html>