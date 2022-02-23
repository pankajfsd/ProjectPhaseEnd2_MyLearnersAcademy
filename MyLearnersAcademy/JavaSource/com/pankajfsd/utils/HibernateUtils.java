package com.pankajfsd.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory factory = null;

	public static SessionFactory getFactory() {

		if (factory == null) {
			try {
				factory = new Configuration().configure("/com/pankajfsd/utils/hibernate.cfg.xml").buildSessionFactory();

				// session = factory.openSession();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return factory;

	}

}
