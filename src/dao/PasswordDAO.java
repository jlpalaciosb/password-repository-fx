package dao;

import entity.Password;
import entity.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class PasswordDAO {
    
    public List<Password> get(User user, String site) {
        Session session = null;
        Transaction trans = null;
        List<Password> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            Query query = session.createQuery(
                "from Password where UserID = :uid and Site like concat('%', :site, '%') order by Site"
            );
            query.setInteger("uid", user.getId());
            query.setString("site", site);
            result = query.list();
            trans.commit();
        } catch(HibernateException ex) {
            if(trans != null) trans.rollback();
            ex.printStackTrace(System.err);
        } finally {
            if(session != null) session.close();
        }
        return result;
    }
    
    public boolean save(Password pwd) {
        Session session = null;
        Transaction trans = null;
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.persist(pwd);
            trans.commit();
            success = true;
        } catch(HibernateException ex) {
            if(trans != null) trans.rollback();
            ex.printStackTrace(System.err);
        } finally {
            if(session != null) session.close();
        }
        return success;
    }
    
    public boolean update(Password pwd) {
        Session session = null;
        Transaction trans = null;
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.update(pwd);
            trans.commit();
            success = true;
        } catch(HibernateException ex) {
            if(trans != null) trans.rollback();
            ex.printStackTrace(System.err);
        } finally {
            if(session != null) session.close();
        }
        return success;
    }
    
    public boolean delete(Password pwd) {
        Session session = null;
        Transaction trans = null;
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            session.delete(pwd);
            trans.commit();
            success = true;
        } catch(HibernateException ex) {
            if(trans != null) trans.rollback();
            ex.printStackTrace(System.err);
        } finally {
            if(session != null) session.close();
        }
        return success;
    }
    
}
