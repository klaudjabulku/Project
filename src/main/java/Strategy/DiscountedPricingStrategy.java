package Strategy;

import Entities.Room;

import java.util.Date;

public class DiscountedPricingStrategy implements PricingStrategy {

    private static final double DISCOUNT_FOR_TWO_DAYS = 0.9;
    private static final double DISCOUNT_FOR_SEVEN_DAYS = 0.7;

    @Override
    public double calculatePrice(Room room, Date checkInDate, Date checkOutDate) {
        long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        long numberOfDays = diffInMillies / (1000 * 60 * 60 * 24);

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


