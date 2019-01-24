package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/* This class keeps the Hibernate Session Factory */
public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    public static void buildSessionFactory() throws Exception {
        sessionFactory = new Configuration().configure("/config/hibernate.cfg.xml").buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
