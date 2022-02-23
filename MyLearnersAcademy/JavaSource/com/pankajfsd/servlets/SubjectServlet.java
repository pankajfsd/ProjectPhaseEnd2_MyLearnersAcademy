package com.pankajfsd.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.SubjectDAO;
import com.pankajfsd.entity.Subject;

public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SubjectDAO SubjectDAO = null;

	public SubjectServlet() {
		super();
	}

	public void init() {
		SubjectDAO = new SubjectDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {

			case "createSubject":
				createSubject(request, response);
				break;

			case "getAllSubject":
				getSubjects(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSubjects(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Subject> listSubjects = SubjectDAO.getAllSubject(request, response);
		// System.out.println(listSubjects);

		try {
			HttpSession session = request.getSession();
			session.setAttribute("Subjects", listSubjects);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listSubject.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Subject createSubject(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		Subject newSubject = new Subject();
		newSubject.setName(name);
		SubjectDAO = new SubjectDAO();
		SubjectDAO.saveSubject(newSubject, request, response);
		return newSubject;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
