package Repository;

import Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionConfiguration {
    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Customer.class)
            .addAnnotatedClass(Payment.class)
            .addAnnotatedClass(Hotel.class)
            .addAnnotatedClass(Room.class)
            .addAnnotatedClass(Reservations.class)
            .buildSessionFactory();

    public static SessionFactory getInstance() {
        return sessionFactory;
    }

}
