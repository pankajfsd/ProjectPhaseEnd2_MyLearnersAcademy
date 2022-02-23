package com.pankajfsd.deleteservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pankajfsd.dao.ClassRoomDAO;
import com.pankajfsd.entity.ClassRoom;

public class DeleteOrEditClassRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteOrEditClassRoom() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String classId = request.getParameter("id");
		
		ClassRoomDAO classRoomDao = new ClassRoomDAO();
//		System.out.println(action+" "+classId);
		
	 try {
		 switch(action) {
		 case "edit":
			 ClassRoom classRoom = classRoomDao.editClassRoom(classId);
			 try {
					HttpSession session = request.getSession();
					session.setAttribute("classRoom", classRoom);
					RequestDispatcher dispatcher = request.getRequestDispatcher("editClass.jsp");
					dispatcher.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			 break;
		 case "delete":
			 classRoomDao.deleteClassRoom(request, response, classId);
			 break;
		 case "update":
			 String id = request.getParameter("id");
			 String name = request.getParameter("name");
			 ClassRoom newClass = new ClassRoom();
			 newClass.setClassName(name);
			 classRoomDao.updateClassRoom(newClass, id, request, response);
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
