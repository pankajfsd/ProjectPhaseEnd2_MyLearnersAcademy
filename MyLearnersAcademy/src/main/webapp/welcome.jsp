
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Creation Page</title>
</head>
<body>
	<center>
		<%
		Object message = request.getAttribute("message");
		if (message != null) {
			out.println(message);
		}
		%>
	</center>
	<center>
		<h2>
			<a href="logout.jsp">Logout</a>
		</h2>
		<h1>Student Management</h1>
		<h2>
			<a href="createStudent.jsp">Add New Student</a> <a
				href="/MyLearnersAcademy/student?action=getAllStudent">List All
				Students</a>
		</h2>

		<h1>ClassRoom Management</h1>
		<h2>
			<a href="createClass.jsp">Add New Class</a> <a
				href="/MyLearnersAcademy/classroom?action=getAllClassRoom">List
				All Classes</a>
		</h2>
		<h1>Teacher Management</h1>
		<h2>
			<a href="createTeacher.jsp">Add New Teacher</a> <a
				href="/MyLearnersAcademy/teacher?action=getAllTeacher">List All
				Teachers</a>
		</h2>
		<h1>Subject Management</h1>
		<h2>
			<a href="createSubject.jsp">Add New Subject</a> <a
				href="/MyLearnersAcademy/subject?action=getAllSubject">List All
				Subjects</a>
		</h2>
	</center>

</body>
</html>