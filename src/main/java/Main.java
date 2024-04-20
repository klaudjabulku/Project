import Entities.Customer;
import Entities.Hotel;
import Entities.Payment;
import Entities.Room;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Hotel.class)
                .addAnnotatedClass(Room.class)
                .buildSessionFactory();
    }
}
