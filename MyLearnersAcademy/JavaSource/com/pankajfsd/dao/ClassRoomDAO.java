package com.pankajfsd.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.pankajfsd.entity.ClassRoom;
import com.pankajfsd.utils.HibernateUtils;

public class ClassRoomDAO {

	public void saveClassRoom(ClassRoom classRoom, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Transaction tx = null;
		Session session = null;
		SessionFactory factory = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(classRoom);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // if exception occurred then rollback any new created object which got
								// committed
								// to DB
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		String message = "ClassRoom : " + classRoom.getClassName() + " added successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createClass.jsp");
		dispatcher.forward(request, response);

	}

	@SuppressWarnings("unchecked")
	public List<ClassRoom> getAllClassRoom(HttpServletRequest request, HttpServletResponse response) {
		List<ClassRoom> listClassRoom = null;
		Transaction tx = null;
		Session session = null;
		SessionFactory factory = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the ClassRoom objects
			listClassRoom = session.createQuery("from ClassRoom").getResultList(); // select * from classroom;

			// commit the transaction
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return listClassRoom;
	}

	public ClassRoom getClassRoomById(int class_id) {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		ClassRoom classRoom = null;

		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.getTransaction();
			classRoom = session.get(ClassRoom.class, class_id);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return classRoom;

	}

	public void deleteClassRoom(HttpServletRequest request, HttpServletResponse response, String classId)
			throws ServletException, IOException {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		ClassRoom classRoom = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			classRoom = session.get(ClassRoom.class, Integer.parseInt(classId));
			session.delete(classRoom);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		// forwarding
		String message = "ClassRoom : " + classRoom.getClassName() + " deleted successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);
	}

	public ClassRoom editClassRoom(String classId) {
		Object classRoom = null;
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the ClassRoom objects\
			Query query = session.createQuery("from ClassRoom where class_id =: i");
			query.setParameter("i", Integer.parseInt(classId));
			classRoom = query.getSingleResult();
			// from
			// ClassRoom
			// where
			// class_id
			// =
			// classId;

			// commit the transaction
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return (ClassRoom) classRoom;
	}

	public void updateClassRoom(ClassRoom newClass, String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update ClassRoom set className =: n where class_id =: i");

			query.setParameter("n", newClass.getClassName());
			query.setParameter("i", Integer.parseInt(id));

			int result = query.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // if exception occurred then rollback any new created object which got
								// committed
								// to DB
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		String message = "ClassRoom : " + newClass.getClassName() + " updated successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);		
	}

}
