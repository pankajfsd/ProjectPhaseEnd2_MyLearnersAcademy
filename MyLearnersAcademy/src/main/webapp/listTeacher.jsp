
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.pankajfsd.entity.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get All Classes</title>
</head>
<body>
	<%
	List<Teacher> Teachers = (List<Teacher>) session.getAttribute("Teachers");
	%>
	<center>
		<h1>Teacher Management</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a href="createTeacher.jsp">Add New
				Teacher</a> <a href="welcome.jsp">Welcome Page</a>
			<table border="1" cellpadding="5">
				<caption>
					<h2>List of Teachers</h2>
				</caption>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="Teacher" items="${Teachers}">
					<tr>
						<td><c:out value="${Teacher.teacher_id}" /></td>
						<td><c:out value="${Teacher.name}" /></td>
						<td><a href="/MyLearnersAcademy/deleteoreditteacher?action=edit&id=<c:out value='${Teacher.teacher_id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/MyLearnersAcademy/deleteoreditteacher?action=delete&id=<c:out value='${Teacher.teacher_id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</h2>
	</center>

</body>
</html>

