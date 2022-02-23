package com.pankajfsd.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.TeacherDAO;
import com.pankajfsd.entity.Teacher;

public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TeacherDAO TeacherDAO = null;

	public TeacherServlet() {
		super();
	}

	public void init() {
		TeacherDAO = new TeacherDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {

			case "createTeacher":
				createTeacher(request, response);
				break;

			case "getAllTeacher":
				getTeachers(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getTeachers(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Teacher> listTeachers = TeacherDAO.getAllTeacher(request, response);
		// System.out.println(listTeachers);

		try {
			HttpSession session = request.getSession();
			session.setAttribute("Teachers", listTeachers);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listTeacher.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Teacher createTeacher(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		Teacher newTeacher = new Teacher();
		newTeacher.setName(name);
		TeacherDAO = new TeacherDAO();
		TeacherDAO.saveTeacher(newTeacher, request, response);
		return newTeacher;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
