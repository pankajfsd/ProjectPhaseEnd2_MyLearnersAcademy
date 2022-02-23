
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.pankajfsd.entity.ClassRoom"%>
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
	List<ClassRoom> ClassRooms = (List<ClassRoom>) session.getAttribute("ClassRooms");
	%>
	<center>
		<h1>ClassRoom Management</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a href="createClass.jsp">Add New
				ClassRoom</a> <a href="welcome.jsp">Welcome Page</a>
			<table border="1" cellpadding="5">
				<caption>
					<h2>List of ClassRooms</h2>
				</caption>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="ClassRoom" items="${ClassRooms}">
					<tr>
						<td><c:out value="${ClassRoom.class_id}" /></td>
						<td><c:out value="${ClassRoom.className}" /></td>
						<td><a
							href="/MyLearnersAcademy/deleteoreditclass?action=edit&id=<c:out value='${ClassRoom.class_id}'/>">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/MyLearnersAcademy/deleteoreditclass?action=delete&id=<c:out value='${ClassRoom.class_id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</h2>
	</center>

</body>
</html>

