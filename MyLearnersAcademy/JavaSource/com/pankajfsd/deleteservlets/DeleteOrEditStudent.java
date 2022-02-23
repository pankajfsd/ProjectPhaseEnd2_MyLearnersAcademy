package com.pankajfsd.deleteservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.StudentDAO;
import com.pankajfsd.entity.Student;

public class DeleteOrEditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteOrEditStudent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String studentId = request.getParameter("id");
		
		StudentDAO studentDao = new StudentDAO();
		Student student = new Student();
//		System.out.println(action+" "+studentId);
		
	 try {
		 switch(action) {
		 case "edit":
			 student = studentDao.editStudent(studentId);
				try {
					HttpSession session = request.getSession();
					session.setAttribute("student", student);
					RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
					dispatcher.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			 break;
		 case "delete":
			 studentDao.deleteStudent(request, response, studentId);
			 break;
		 
		 case "update":
			 String id = request.getParameter("id");
			 String name = request.getParameter("name");
			 String email = request.getParameter("email");
			 Student newStudent = new Student();
			 newStudent.setName(name);
			 newStudent.setEmail(email);
			 studentDao = new StudentDAO();
			 studentDao.updateStudent(newStudent, id, request, response);
		 
	 }
		 }catch (Exception e) {
		e.printStackTrace();
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
