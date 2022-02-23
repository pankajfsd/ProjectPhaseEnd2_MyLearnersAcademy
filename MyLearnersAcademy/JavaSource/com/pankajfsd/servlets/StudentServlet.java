package com.pankajfsd.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.StudentDAO;
import com.pankajfsd.entity.Student;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDAO studentDAO = null;

	public StudentServlet() {
		super();
	}

	public void init() {
		studentDAO = new StudentDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {

			case "createStudent":
				createStudent(request, response);
				break;

			case "getAllStudent":
				getStudents(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getStudents(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Student> listStudents = studentDAO.getAllStudents(request, response);
		// System.out.println(listStudents);

		try {
			HttpSession session = request.getSession();
			session.setAttribute("students", listStudents);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listStudent.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Student createStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Student newStudent = new Student();
		newStudent.setName(name);
		newStudent.setEmail(email);
		studentDAO = new StudentDAO();
		studentDAO.saveStudent(newStudent, request, response);
		return newStudent;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
