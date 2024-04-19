package Strategy;

import Entities.Room;
import java.util.Date;

public interface PricingStrategy {
    double calculatePrice(Room room, Date checkInDate, Date checkOutDate);
}

