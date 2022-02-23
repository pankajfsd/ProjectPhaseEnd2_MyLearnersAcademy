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

import com.pankajfsd.entity.Subject;
import com.pankajfsd.utils.HibernateUtils;

public class SubjectDAO {

	public void saveSubject(Subject subject, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Transaction tx = null;
		Session session = null;
		SessionFactory factory = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(subject);
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
		String message = "Subject : " + subject.getName() + " added successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createSubject.jsp");
		dispatcher.forward(request, response);

	}

	@SuppressWarnings("unchecked")
	public List<Subject> getAllSubject(HttpServletRequest request, HttpServletResponse response) {
		List<Subject> listsubject = null;
		Transaction tx = null;
		Session session = null;
		SessionFactory factory = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the Subject objects
			listsubject = session.createQuery("from Subject").getResultList(); // select * from subject;

			// commit the transaction
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return listsubject;
	}

	public Subject getSubjectById(int subject_id) {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		Subject subject = null;

		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.getTransaction();
			subject = session.get(Subject.class, subject_id);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return subject;

	}

	public Subject editSubject(String subjectId) {
		Object subject = null;
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;

		try {

			factory = HibernateUtils.getFactory();
			session = factory.openSession();

			// Start transaction
			tx = session.beginTransaction();

			// Get the subject objects\
			Query query = session.createQuery("from Subject where subject_id =: i");
			query.setParameter("i", Integer.parseInt(subjectId));
			subject = query.getSingleResult();
			// from
			// subject
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
		return (Subject) subject;		
	}

	public void deleteSubject(HttpServletRequest request, HttpServletResponse response, String subjectId) throws ServletException, IOException {
		Session session = null;
		SessionFactory factory = null;
		Transaction tx = null;
		Subject subject = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			subject = session.get(Subject.class, Integer.parseInt(subjectId));
			session.delete(subject);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		// forwarding
		String message = "Subject : " + subject.getName() + " deleted successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);		
	}

	public void updateSubject(Subject updateSubject, String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Transaction tx = null;
		SessionFactory factory = null;
		Session session = null;
		try {
			factory = HibernateUtils.getFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("update Subject set name =: n where subject_id =: i");

			query.setParameter("n", updateSubject.getName());
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
		String message = "Teacher : " + updateSubject.getName() + " updated successfully.";
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(request, response);			
	}

}
