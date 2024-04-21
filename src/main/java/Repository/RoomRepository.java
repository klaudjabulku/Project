package Repository;

import Entities.Customer;
import Entities.Hotel;
import Entities.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private SessionFactory sessionFactory;

    public RoomRepository() {
        sessionFactory = SessionConfiguration.getInstance();
    }

    public void save(Room room) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(room);
            transaction.commit();
        }
    }

    public void update(Room room) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(room);
            transaction.commit();
        }
    }

    public  Room findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Room.class, id);
        }
    }
    public  List<Room> getRoomsByHotelId(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT r FROM Room r " +
                " join r.hotel h " +
                "WHERE h.id = ?1 AND availability = true";
        Session session = sessionFactory.openSession();
        rooms = session.createQuery(sql, Room.class)
                .setParameter(1,hotelId)
                .getResultList();
        session.close();
        return rooms;
    }

    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room = session.find(Room.class, id);
            session.delete(room);
            transaction.commit();
            System.out.println("Room with id " + id + " was deleted successfully");
        }
    }

    public void markAvailability(Integer id, boolean availability) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room = session.find(Room.class, id);
            room.setAvailability(availability);
            session.update(room);
            transaction.commit();
            System.out.println("Availability of room with id " + id + " was updated successfully");
        }
    }

    public void updatePrice(Integer id, double price) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room = session.find(Room.class, id);
            room.setPrice(price);
            session.update(room);
            transaction.commit();
            System.out.println("Price of room with id " + id + " was updated successfully");
        }
    }

    public void updateDates(Integer id, Date dates) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room = session.find(Room.class, id);
            room.setDates(dates);
            session.update(room);
            transaction.commit();
            System.out.println("Dates of room with id " + id + " were updated successfully");
        }
    }

    public List<Room> findAvailableRooms(Date checkInDate, Date checkOutDate, int requiredCapacity) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT r FROM Room r WHERE r.availability = true " +
                    "AND r.roomCapacity >= :requiredCapacity " +
                    "AND NOT EXISTS (" +
                    "  SELECT 1 FROM Reservation res " +
                    "  WHERE res.room = r " +
                    "  AND ((:checkInDate BETWEEN res.checkInDate AND res.checkOutDate) " +
                    "       OR (:checkOutDate BETWEEN res.checkInDate AND res.checkOutDate))" +
                    ")";
            Query<Room> query = session.createQuery(hql, Room.class);
            query.setParameter("requiredCapacity", requiredCapacity);
            query.setParameter("checkInDate", checkInDate);
            query.setParameter("checkOutDate", checkOutDate);
            return query.list();
        }
    }





}


