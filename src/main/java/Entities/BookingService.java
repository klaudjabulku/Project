package Entities;

import java.util.List;

public class BookingService {

    // Book room test github

    public void bookRoom(Hotel hotel, Room room, List<Customer> customers) {
        for (Customer customer : customers) {
            room.addObserver(customer);
        }

        if (!HotelAvailabilityChecker.isAvailable(hotel)) {
            System.out.println("No available rooms in the hotel.");
        } else {
            room.notifyObservers(hotel);
        }
    }
}
