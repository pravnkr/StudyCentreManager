<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Update Successful</title>
</head>
<body>
	<i>The Record is Updated Successfully</i>
	<h2>Summary of update</h2>
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
			<td><c:out value="${updatedStudent.firstname}" /></td>
			<td><c:out value="${updatedStudent.lastname}" /></td>
			<td><c:out value="${updatedStudent.enrollNo}" /></td>
			<td><c:out value="${updatedSstudent.email}" /></td>
			<td><c:out value="${updatedStudent.mob}" /></td>
			<td><c:out value="${updatedStudent.programme}" /></td>
			<td><c:out value="${updatedStudent.semester}" /></td>
		</tr>
		
	</table>
</body>
</html>