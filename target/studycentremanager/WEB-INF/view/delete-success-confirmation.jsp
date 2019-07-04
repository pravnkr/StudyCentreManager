<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IgnouBadhega - Delete Successful</title>
</head>
<body>

	<i>The Record is Deleted Successfully</i>
	<h2>Summary of delete</h2>
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
			<td><c:out value="${deletedStudent.firstname}" /></td>
			<td><c:out value="${deletedStudent.lastname}" /></td>
			<td><c:out value="${deletedStudent.enrollNo}" /></td>
			<td><c:out value="${deletedSstudent.email}" /></td>
			<td><c:out value="${deletedStudent.mob}" /></td>
			<td><c:out value="${deletedStudent.programme}" /></td>
			<td><c:out value="${deletedStudent.semester}" /></td>
		</tr>
		
	</table>

</body>
</html>