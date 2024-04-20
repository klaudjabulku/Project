package Strategy;

import Entities.Room;

import java.time.LocalDate;
import java.util.Date;

public interface PricingStrategy {
    double calculatePrice(Room room, LocalDate checkInDate, LocalDate checkOutDate);
}

