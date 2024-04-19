package Entities;

public class HotelAvailabilityChecker {
    public static boolean isAvailable(Hotel hotel) {

        for (Room room : hotel.getRooms()) {
            if (room.isAvailability()) {

                return true;
            }
        }

        return false;
    }
}
