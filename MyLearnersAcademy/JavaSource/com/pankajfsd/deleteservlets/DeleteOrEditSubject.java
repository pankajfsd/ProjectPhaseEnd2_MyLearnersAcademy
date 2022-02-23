package com.pankajfsd.deleteservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.SubjectDAO;
import com.pankajfsd.entity.Subject;

public class DeleteOrEditSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteOrEditSubject() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("Reached
		// com.pankajfsd.deleteservlets.DeleteOrEditSubject");
		String action = request.getParameter("action");
		String subjectId = request.getParameter("id");

		SubjectDAO subjectDao = new SubjectDAO();
		System.out.println(action + " " + subjectId);

		try {
			switch (action) {
			case "edit":
				Object subject = subjectDao.editSubject(subjectId);
				try {
					HttpSession session = request.getSession();
					session.setAttribute("subject", subject);
					RequestDispatcher dispatcher = request.getRequestDispatcher("editSubject.jsp");
					dispatcher.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "delete":
				subjectDao.deleteSubject(request, response, subjectId);
				break;
			case "update":
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				Subject updateSubject = new Subject();
				updateSubject.setName(name);
				subjectDao.updateSubject(updateSubject, id, request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
