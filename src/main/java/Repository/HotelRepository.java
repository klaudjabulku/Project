package Repository;

import Entities.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HotelRepository {

    private static SessionFactory sessionFactory;

    public HotelRepository() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public void save(Hotel hotel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(hotel);
            transaction.commit();
        }
    }

    public void update(Hotel hotel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(hotel);
            transaction.commit();
        }
    }

    public static Hotel findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Hotel.class, id);
        }
    }

    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Hotel hotel = session.find(Hotel.class, id);
            session.delete(hotel);
            transaction.commit();
            System.out.println("Hotel with id " + id + " was deleted successfully");
        }
    }

    public List<Hotel> getAllHotelsWithRooms() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Hotel> query = builder.createQuery(Hotel.class);
            Root<Hotel> root = query.from(Hotel.class);
            root.fetch("rooms");
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }

    public static List<Hotel> getHotelsByCity(String city) {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT name, numberOfRooms FROM Hotels WHERE location = ?";
        try (Session session = sessionFactory.openSession()) {
            hotels = session.createQuery(sql, Hotel.class)
                    .setParameter(1, city)
                    .getResultList();
        }
        return hotels;
    }
}
