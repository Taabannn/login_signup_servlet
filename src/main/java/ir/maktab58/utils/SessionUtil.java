package ir.maktab58.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Taban Soleymani
 */
public class SessionUtil {
    private static SessionUtil instance = new SessionUtil();
    private SessionFactory sessionFactory;

    public static SessionUtil getInstance() {
        return instance;
    }

    private SessionUtil() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        Session session = getInstance().sessionFactory.openSession();
        return session;
    }
}
