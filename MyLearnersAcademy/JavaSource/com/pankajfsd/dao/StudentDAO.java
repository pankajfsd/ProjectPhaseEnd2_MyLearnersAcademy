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

import com.pankajfsd.entity.Student;
import com.pankajfsd.utils.HibernateUtils;

public class StudentDAO {

	public void saveStudent(Student student, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(student);
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
		String message = "Student : " + student.getName() + " added successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createStudent.jsp");
		dispatcher.forward(request, response);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Student> listStudents = null;
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the student objects
			listStudents = session.createQuery("from Student").getResultList(); // select * from student;

			// commit the transaction
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return listStudents;

	}

	public Student getStudentById(int student_id) {

		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;
		Student student = null;

		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.getTransaction();
			student = session.get(Student.class, student_id);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return student;

	}

	public Student editStudent(String studentId) {
		Object student = null;
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the student objects\
			Query query = session.createQuery("from Student where student_id =: i");
			query.setParameter("i", Integer.parseInt(studentId));
			student = query.getSingleResult();
			// from
			// student
			// where
			// student_id
			// =
			// studentId;

			// commit the transaction
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return (Student) student;

	}

	public void deleteStudent(HttpServletRequest request, HttpServletResponse response, String studentId)
			throws ServletException, IOException {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		Student student = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			student = session.get(Student.class, Integer.parseInt(studentId));
			session.delete(student);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		// forwarding
		String message = "Student : " + student.getName() + " deleted successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);

	}

	public void updateStudent(Student student, String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update Student set name =: n, email =: e where student_id =: i");

			query.setParameter("n", student.getName());
			query.setParameter("e", student.getEmail());
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
		String message = "Student : " + student.getName() + " updated successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);
	}

}
