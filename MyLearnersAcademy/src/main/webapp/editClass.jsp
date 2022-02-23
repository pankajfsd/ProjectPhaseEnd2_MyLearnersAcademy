<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.pankajfsd.entity.ClassRoom"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Class</title>
</head>
<body>
	<%
	ClassRoom classRoom = (ClassRoom) session.getAttribute("classRoom");
	//System.out.println(Class);
	%>

	<center>
		<h1>Edit Class</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a
				href="/MyLearnersAcademy/classroom?action=getAllClassRoom">List All
				Classs</a> <a href="welcome.jsp">Welcome Page</a>
			<form action="/MyLearnersAcademy/deleteoreditclass" method="post">
				<table border="1" cellpadding="10">
					<h1>Edit Class</h1>
					<tr>
						<th>ID</th>
						<td><input type="text" name="id" value="<%=classRoom.getClass_id()%>" readonly></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" value="<%=classRoom.getClassName()%>"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="hidden"
							name="action" value="update" /> <input type="submit"
							value="Update Class" /></td>
					</tr>
				</table>
			</form>
		</h2>
	</center>
</body>
</html>