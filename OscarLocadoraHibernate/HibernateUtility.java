

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtility {
	private static SessionFactory factory;
	static {
	try {
	factory = new Configuration().configure().buildSessionFactory();
	} catch (Exception e) {
	e.printStackTrace();
	factory = null;
	}
	}
	public static Session getSession() {
	return factory.openSession();
	}
}
