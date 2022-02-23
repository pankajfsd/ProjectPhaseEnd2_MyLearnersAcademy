<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.pankajfsd.entity.Student"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Student</title>
</head>
<body>
	<%
	Student student = (Student) session.getAttribute("student");
	//System.out.println(student);
	%>

	<center>
		<h1>Edit Student</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a
				href="/MyLearnersAcademy/student?action=getAllStudent">List All
				Students</a> <a href="welcome.jsp">Welcome Page</a>
			<form action="/MyLearnersAcademy/deleteoreditstudent" method="post">
				<table border="1" cellpadding="10">
					<h1>Edit Student</h1>
					<tr>
						<th>ID</th>
						<td><input type="text" name="id" value="<%=student.getStudent_id()%>" readonly></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" value="<%=student.getName()%>"></td>
					</tr>
					<tr>
						<th>Email</th>
						<td><input type="email" name="email" value="<%=student.getEmail()%>"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="hidden"
							name="action" value="update" /> <input type="submit"
							value="Update Student" /></td>
					</tr>
				</table>
			</form>
		</h2>
	</center>
</body>
</html>