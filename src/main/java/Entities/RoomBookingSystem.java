//package Entities;
//
//import Repository.CustomerRepository;
//import Repository.RoomRepository;
//import Strategy.PricingStrategy;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//
//public class RoomBookingSystem {
//
//    private RoomRepository roomRepository;
//    private CustomerRepository customerRepository;
//    private PricingStrategy pricingStrategy;
//
//    public RoomBookingSystem() {
//        this.roomRepository = new RoomRepository();
//        this.customerRepository = new CustomerRepository();
//    }
//
//    public boolean isRoomAvailable(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
//        for (Room room : hotel.getRooms()) {
//            if (room.getRoomType().equals(roomType) && room.isAvailability() &&
//                    room.getCheckInDate().isBefore(checkOutDate) && room.getCheckOutDate().isAfter(checkInDate)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void setPricingStrategy(PricingStrategy pricingStrategy) {
//        this.pricingStrategy = pricingStrategy;
//    }
//
//    public double getPricePerDay(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
//        if (pricingStrategy != null) {
//            return pricingStrategy.calculatePrice(room, checkInDate, checkOutDate);
//        } else {
//            return room.getPrice();
//        }
//    }
//
//    public void bookRoom(Customer customer, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate, String emailAddress, String phoneNumber) {
//        if (isRoomAvailable(hotel, checkInDate, checkOutDate, room.getRoomType())) {
//            double totalPrice = getPricePerDay(room, checkInDate, checkOutDate);
//
//            String bookingConfirmation = "Booking Confirmation:\n\n" +
//                    "Hotel Name: " + hotel.getName() + "\n" +
//                    "Room Type: " + room.getRoomType() + "\n" +
//                    "Check-in Date: " + checkInDate.toString() + "\n" +
//                    "Check-out Date: " + checkOutDate.toString() + "\n" +
//                    "Name: " + customer.getName() + " " + customer.getSurname() + "\n" +
//                    "Email Address: " + emailAddress + "\n" +
//                    "Phone Number: " + phoneNumber + "\n" +
//                    "Total Price: LEK " + totalPrice;
//
//            writeBookingConfirmationToFile(customer.getName(), bookingConfirmation);
//
//            room.setAvailability(false);
//        } else {
//            System.out.println("Sorry, the room is not available for the specified dates.");
//        }
//    }
//
//    private void writeBookingConfirmationToFile(String customerName, String bookingConfirmation) {
//        String fileName = customerName + "_Booking_Confirmation.txt";
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            writer.write(bookingConfirmation);
//            System.out.println("Booking confirmation has been written to file: " + fileName);
//        } catch (IOException e) {
//            System.err.println("Error writing booking confirmation to file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
