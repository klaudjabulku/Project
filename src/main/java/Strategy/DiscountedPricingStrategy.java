package Strategy;

import Entities.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DiscountedPricingStrategy implements PricingStrategy {

    private static final double DISCOUNT_FOR_TWO_DAYS = 0.9;
    private static final double DISCOUNT_FOR_SEVEN_DAYS = 0.7;

    @Override
    public double calculatePrice(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        long numberOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        double totalPrice;
        if (numberOfDays > 7) {
            totalPrice = room.getPrice() * numberOfDays * DISCOUNT_FOR_SEVEN_DAYS;
        } else if (numberOfDays > 2) {
            totalPrice = room.getPrice() * numberOfDays * DISCOUNT_FOR_TWO_DAYS;
        } else {
            totalPrice = room.getPrice() * numberOfDays;
        }

        return totalPrice;
    }
}


