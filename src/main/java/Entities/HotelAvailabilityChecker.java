package Entities;

import java.util.List;

public class HotelAvailabilityChecker {
    public static boolean isAvailable(List<Room> rooms) {

        for (Room room : rooms) {
            if (room.isAvailability()) {
                return true;
            }
        }

        return false;
    }
}
