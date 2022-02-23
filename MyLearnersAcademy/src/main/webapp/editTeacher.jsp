<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.pankajfsd.entity.Teacher"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Teacher</title>
</head>
<body>
	<%
	Teacher teacher = (Teacher) session.getAttribute("teacher");
	//System.out.println(Teacher);
	%>

	<center>
		<h1>Edit Teacher</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a
				href="/MyLearnersAcademy/teacher?action=getAllTeacher">List All
				Teachers</a> <a href="welcome.jsp">Welcome Page</a>
			<form action="/MyLearnersAcademy/deleteoreditteacher" method="post">
				<table border="1" cellpadding="10">
					<h1>Edit Teacher</h1>
					<tr>
						<th>ID</th>
						<td><input type="text" name="id" value="<%=teacher.getTeacher_id()%>" readonly></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" value="<%=teacher.getName()%>"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="hidden"
							name="action" value="update" /> <input type="submit"
							value="Update Teacher" /></td>
					</tr>
				</table>
			</form>
		</h2>
	</center>
</body>
</html>