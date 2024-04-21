package Repository;

import Entities.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PaymentRepository {
    private final SessionFactory sessionFactory;

    public PaymentRepository() {
       sessionFactory = SessionConfiguration.getInstance();
    }

    public void save(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
        }
    }

    public void update(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(payment);
            transaction.commit();
        }
    }

    public Payment findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Payment.class, id);
        }
    }

    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Payment payment = session.find(Payment.class, id);
            session.delete(payment);
            transaction.commit();
            System.out.println("Payment with id " + id + " was deleted successfully");
        }
    }
}
