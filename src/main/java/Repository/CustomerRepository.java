package Repository;

import Entities.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CustomerRepository {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();

        }
        catch (HibernateException ex) {

            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void save(Customer customer) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.persist(customer);

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void update(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.find(Customer.class, id);
            session.delete(customer);
            transaction.commit();
            System.out.println("Customer with id " + id + " was deleted successfully ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
