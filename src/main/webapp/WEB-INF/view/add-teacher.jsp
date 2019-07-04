<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<span> <a href="${pageContext.request.contextPath}">Dashboard</a>
	</span>

	<h3>Save A New Teacher</h3>
	<form:form
		action="${pageContext.request.contextPath}/coordinator/add-teacher/save"
		modelAttribute="teacher">

		<table>

			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mob</th>
				<th>Subjects</th>
			</tr>
				<tr>
					<td><form:input path="firstname" /><br>
						<form:errors path="firstname" /></td>
					<td><form:input path="lastname" /><br>
						<form:errors path="lastname" /></td>
					<td><form:input path="email" /><br>
						<form:errors path="email" /></td>
					<td><form:input path="mob" /><br>
						<form:errors path="mob" /></td>
					<td><form:select path="subjects" multiple="multiple" >
							<form:options items="${teacher.subjects}" />
						</form:select>
						<br> <form:errors path="subjects" /></td>
				</tr>

			<tr>
				<td><input type="submit" value="save" /></td>
			</tr>

		</table>

	</form:form>

	<c:if test="${dupSaveError != null}">

		<i>Teacher is allready persistent in records.</i>
		<br>

		<table>

			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mob</th>
			</tr>
			<tr>
				<td><c:out value="${dupTeacher.firstname}" /></td>
				<td><c:out value="${dupTeacher.lastname}" /></td>
				<td><c:out value="${dupTeacher.email}" /></td>
				<td><c:out value="${dupTeacher.mob}" /></td>
			</tr>

		</table>
	</c:if>
	
	<c:if test="${saveSuccess != null}">
		Teacher Added Successfully	
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