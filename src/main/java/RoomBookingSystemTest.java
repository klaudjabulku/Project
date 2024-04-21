import Entities.BookingService;
import Entities.Customer;
import Entities.Hotel;
import Entities.Room;
import Repository.HotelRepository;
import Repository.RoomRepository;
import Strategy.DiscountedPricingStrategy;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomBookingSystemTest {

    public static void main(String[] args) {
        HotelRepository hotelRepository = new HotelRepository();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.next();
        System.out.println("NGA CONSOLA" + city);
        List<Hotel> hotels = hotelRepository.getHotelsByCity(city.trim());
        if (hotels.isEmpty()) {
            System.out.println("No hotels found in " + city);
        } else {
            System.out.println("Hotels in " + city + ":");
            for (Hotel hotel : hotels) {
                System.out.println("ID: " + hotel.getId() + ", Name: " + hotel.getName() + ", Rooms: " + hotel.getNumberOfRooms());
            }
            System.out.print("Enter hotel ID to see available rooms: ");
            int hotelId = scanner.nextInt();
            RoomRepository roomRepository=new RoomRepository();
            List<Room> rooms = roomRepository.getRoomsByHotelId(hotelId);
            if (rooms.isEmpty()) {
                System.out.println("No available rooms in the selected hotel.");
            } else {
                for (Room room : rooms) {
                    System.out.println("ID - " +room.getId()+ " Room Capacity: " + room.getRoomCapacity() + ", Price: LEK" + room.getPrice() + ", Available: " + room.isAvailability());
                }
                System.out.println("Select Room ID: ");
                int roomId = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter start date (YYYY-MM-DD): ");
                String startDateStr = scanner.nextLine();
                LocalDate startDate = LocalDate.parse(startDateStr);

                System.out.println("Enter end date (YYYY-MM-DD): ");
                String endDateStr = scanner.nextLine();
                LocalDate endDate = LocalDate.parse(endDateStr);


                Room room = roomRepository.findById(roomId);
                DiscountedPricingStrategy discountedPricingStrategy = new DiscountedPricingStrategy();
                double totalPrice = discountedPricingStrategy.calculatePrice(room,startDate,endDate);

                System.out.println("Total price: LEK " + totalPrice);

                System.out.println("Do you want to proceed with the reservation? (yes/no)");
                String confirmation = scanner.nextLine();

                if ("yes".equalsIgnoreCase(confirmation)) {

                    System.out.println("Please enter your name:");
                    String name = scanner.nextLine();

                    System.out.println("Please enter your surname:");
                    String surname = scanner.nextLine();

                    System.out.println("Please enter your email:");
                    String email = scanner.nextLine();

                    System.out.println("Please enter your phone number:");
                    String phone = scanner.nextLine();

                    List<Customer> customers = new ArrayList<>();
                    customers.add(new Customer(name, surname, email, phone, "", new ArrayList<>()));

                    BookingService bookingService = new BookingService();
                    Hotel hotel = hotelRepository.findById(hotelId);
                    List<Room> roomsOfHotel = roomRepository.getRoomsByHotelId(hotelId);
                    bookingService.bookRoom(hotel, room, customers, roomsOfHotel);
                }

            }
        }
    }
}
