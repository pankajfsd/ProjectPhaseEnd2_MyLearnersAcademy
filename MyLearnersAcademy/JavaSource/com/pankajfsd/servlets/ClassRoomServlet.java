package com.pankajfsd.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.ClassRoomDAO;
import com.pankajfsd.entity.ClassRoom;

public class ClassRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClassRoomDAO ClassRoomDAO = null;

	public ClassRoomServlet() {
		super();
	}

	public void init() {
		ClassRoomDAO = new ClassRoomDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {

			case "createClassRoom":
				createClassRoom(request, response);
				break;

			case "getAllClassRoom":
				getClassRooms(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getClassRooms(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<ClassRoom> listClassRooms = ClassRoomDAO.getAllClassRoom(request, response);
		// System.out.println(listClassRooms);

		try {
			HttpSession session = request.getSession();
			session.setAttribute("ClassRooms", listClassRooms);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listClass.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ClassRoom createClassRoom(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("name");
		ClassRoom newClassRoom = new ClassRoom();
		newClassRoom.setClassName(name);
		ClassRoomDAO = new ClassRoomDAO();
		ClassRoomDAO.saveClassRoom(newClassRoom, request, response);
		return newClassRoom;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
