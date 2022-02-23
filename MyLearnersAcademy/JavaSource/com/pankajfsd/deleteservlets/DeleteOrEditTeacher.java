package com.pankajfsd.deleteservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.TeacherDAO;
import com.pankajfsd.entity.Teacher;

public class DeleteOrEditTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteOrEditTeacher() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("Reached
		// com.pankajfsd.deleteservlets.DeleteOrEditTeacher");
		String action = request.getParameter("action");
		String teacherId = request.getParameter("id");

		TeacherDAO TeacherDao = new TeacherDAO();
		// System.out.println(action+" "+teacherId);

		try {
			switch (action) {
			case "edit":
				Object teacher = TeacherDao.editTeacher(teacherId);
				try {
					HttpSession session = request.getSession();
					session.setAttribute("teacher", teacher);
					RequestDispatcher dispatcher = request.getRequestDispatcher("editTeacher.jsp");
					dispatcher.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "delete":
				TeacherDao.deleteTeacher(request, response, teacherId);
				break;
			case "update":
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				Teacher updateteacher = new Teacher();
				updateteacher.setName(name);
				TeacherDao.updateTeacher(updateteacher, id, request, response);
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
