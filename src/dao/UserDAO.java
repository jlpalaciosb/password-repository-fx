package dao;

import entity.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UserDAO {
    
    public User get(Integer id) {
        return null;
    }
    
    public User get(String name) {
        Session session = null;
        Transaction trans = null;
        User u = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            Query query = session.createQuery("from User where Name = :name");
            query.setParameter("name", name);
            List result = query.list();
            trans.commit();
            if(!result.isEmpty()) u = (User)result.get(0);
        } catch(HibernateException ex) {
            if(trans != null) trans.rollback();
            ex.printStackTrace(System.err);
        } finally {
            if(session != null) session.close();
        }
        return u;
    }
    
    public void save(User user) {
        Session session = null;
        Transaction trans = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.persist(user);
            trans.commit();
        } catch(HibernateException ex) {
            if(trans != null) trans.rollback();
            ex.printStackTrace(System.err);
        } finally {
            if(session != null) session.close();
        }
    }
    
    public void update(User user) {
        
    }
    
    public void delete(User user) {
        
    }
    
    public void delete(Integer id) {
        
    }
    
}
