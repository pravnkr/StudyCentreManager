<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Generate Batch</title>
</head>
<body>

	<span> <a href="${pageContext.request.contextPath}">Dashboard</a>
	</span>

	<h2>IgnouBadhega - Generate Batch Program and Semester Wise.</h2>

	<form:form
		action="${pageContext.request.contextPath}/coordinator/generate-batch/apply"
		modelAttribute="semester">

		<label>Program:</label>
		<form:select path="programme">
			<form:option value="BCA">BCA</form:option>
			<form:option value="MCA">MCA</form:option>
		</form:select>
		
		<br>
		
		<label>Semester:</label>
		<form:select path="semNo">
			<form:option value="1">1st Semester</form:option>
			<form:option value="2">2nd Semester</form:option>
			<form:option value="3">3rd Semester</form:option>
			<form:option value="4">4th Semester</form:option>
			<form:option value="5">5th Semester</form:option>
			<form:option value="6">6th Semester</form:option>
		</form:select>
		
		<br>
		
		<input type="submit" value="Generate a Batch">
		
	</form:form>
	
	<c:if test="${batchGeneratorError != null}">
		<i>${batchGeneratorError}</i>
	</c:if>
	
	<c:if test="${batchGeneratorSuccess != null}">
		<i>${batchGeneratorSuccess}</i>
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