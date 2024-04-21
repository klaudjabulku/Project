package Repository;

import Entities.Customer;
import Entities.Reservations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ReservationRepository {
    private final SessionFactory sessionFactory;

    public ReservationRepository() {
        sessionFactory = SessionConfiguration.getInstance();
    }


    public void save(Reservations reservations) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.persist(reservations);

            transaction.commit();
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

}
