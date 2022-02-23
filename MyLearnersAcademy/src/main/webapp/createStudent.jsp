<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Student</title>
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
		<h1>Student Management</h1>
		<h2>
			<a href="logout.jsp">Logout</a> <a
				href="/MyLearnersAcademy/student?action=getAllStudent">List All
				Students</a>
				<a href="welcome.jsp">Welcome Page</a>
			<form action="/MyLearnersAcademy/student" method="post">
				<table border="1" cellpadding="10">
					<h1>Add New Student</h1>
					<tr>
						<th>Name</th>
						<td><input type="text" name="name" Required></td>
					</tr>
					<tr>
						<th>Email</th>
						<td><input type="email" name="email" Required></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="hidden"
							name="action" value="createStudent" /> <input type="submit"
							value="Add Student" /></td>
					</tr>
				</table>
			</form>
		</h2>
	</center>

</body>
</html>