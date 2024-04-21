package Entities;

import java.util.List;

public class BookingService {



    public void bookRoom(Hotel hotel, Room room, List<Customer> customers, List<Room> roomsOfHotel) {
        for (Customer customer : customers) {
            room.addObserver(customer);
        }

        if (!HotelAvailabilityChecker.isAvailable(roomsOfHotel)) {
            System.out.println("No available rooms in the hotel.");
        } else {
            room.notifyObservers(hotel);
        }
    }
}
