package Observer;

import Entities.Hotel;

public interface HotelAvailabilityObserver {
    void update(Hotel hotel);
}
