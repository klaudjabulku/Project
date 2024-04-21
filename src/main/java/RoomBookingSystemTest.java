import Entities.BookingService;
import Entities.Customer;
import Entities.Hotel;
import Entities.Room;
import Repository.HotelRepository;
import Repository.RoomRepository;
import Strategy.DiscountedPricingStrategy;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
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

                LocalDate today = LocalDate.now();

                boolean validDate = false;
                LocalDate startDate = null;

                while (!validDate) {
                    System.out.println("Enter start date (YYYY-MM-DD): ");
                    String startDateStr = scanner.nextLine();

                    try {
                        startDate = LocalDate.parse(startDateStr);

                        if (!startDate.isBefore(today)) {
                            validDate = true;
                        } else {
                            System.out.println("Please enter a date on or after today.");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                    }
                }

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

                    boolean validEmail = false;
                    String email = "";

                    while (!validEmail) {
                        System.out.println("Please enter your email:");
                        email = scanner.nextLine();

                        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                        if(email.matches(emailRegex)) {
                            validEmail = true;
                        } else {
                            System.out.println("Invalid email address. Please enter a valid email.");
                        }
                    }
                    boolean validPhone = false;
                    String phone = "";

                    while (!validPhone) {
                        System.out.println("Please enter your phone number:");
                        phone = scanner.nextLine();
                        String phoneRegex = "^(\\+?[1-9]\\d{1,3}[- ]?)?(\\(?\\d{3}\\)?[- ]?)?\\d{3}[- ]?\\d{4}$";

                        if(phone.length() >= 10 && phone.length() <= 15 && phone.matches(phoneRegex)) {
                            System.out.println("Valid phone number: " + phone);
                            validPhone = true;
                        } else {
                            System.out.println("Invalid phone number. Please enter a valid phone number.");
                        }
                    }
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
