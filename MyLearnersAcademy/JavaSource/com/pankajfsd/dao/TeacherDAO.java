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

import com.pankajfsd.entity.Teacher;
import com.pankajfsd.entity.Teacher;
import com.pankajfsd.utils.HibernateUtils;

public class TeacherDAO {

	public void saveTeacher(Teacher Teacher, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Transaction tx = null;
		Session session = null;
		SessionFactory factory = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(Teacher);
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
		String message = "Teacher : " + Teacher.getName() + " added successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createTeacher.jsp");
		dispatcher.forward(request, response);

	}

	@SuppressWarnings("unchecked")
	public List<Teacher> getAllTeacher(HttpServletRequest request, HttpServletResponse response) {
		List<Teacher> listTeacher = null;
		Transaction tx = null;
		Session session = null;
		SessionFactory factory = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the Teacher objects
			listTeacher = session.createQuery("from Teacher").getResultList(); // select * from Teacher;

			// commit the transaction
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return listTeacher;
	}

	public Teacher getTeacherById(int teacher_id) {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		Teacher Teacher = null;

		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.getTransaction();
			Teacher = session.get(Teacher.class, teacher_id);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return Teacher;

	}

	public Teacher editTeacher(String teacherId) {
		Object teacher = null;
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the Teacher objects\
			Query query = session.createQuery("from Teacher where teacher_id =: i");
			query.setParameter("i", Integer.parseInt(teacherId));
			teacher = query.getSingleResult();
			// from
			// Teacher
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
		return (Teacher) teacher;
	}

	public void deleteTeacher(HttpServletRequest request, HttpServletResponse response, String teacherId)
			throws ServletException, IOException {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		Teacher teacher = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			teacher = session.get(Teacher.class, Integer.parseInt(teacherId));
			session.delete(teacher);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		// forwarding
		String message = "Teacher : " + teacher.getName() + " deleted successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);
	}

	public void updateTeacher(Teacher updateteacher, String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update Teacher set name =: n where teacher_id =: i");

			query.setParameter("n", updateteacher.getName());
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
		String message = "Teacher : " + updateteacher.getName() + " updated successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);		
	}

}
