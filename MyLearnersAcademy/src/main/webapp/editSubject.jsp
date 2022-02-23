<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.pankajfsd.entity.Subject"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Subject</title>
</head>
<body>
	<%
	Subject subject = (Subject) session.getAttribute("subject");
	//System.out.println(Subject);
	%>

	<center>
		<h1>Edit Subject</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a
				href="/MyLearnersAcademy/subject?action=getAllSubject">List All
				Subjects</a> <a href="welcome.jsp">Welcome Page</a>
			<form action="/MyLearnersAcademy/deleteoreditsubject" method="post">
				<table border="1" cellpadding="10">
					<h1>Edit Subject</h1>
					<tr>
						<th>ID</th>
						<td><input type="text" name="id" value="<%=subject.getSubject_id()%>" readonly></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" value="<%=subject.getName()%>"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="hidden"
							name="action" value="update" /> <input type="submit"
							value="Update Subject" /></td>
					</tr>
				</table>
			</form>
		</h2>
	</center>
</body>
</html>