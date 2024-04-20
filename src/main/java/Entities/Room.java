package Entities;


import Observer.HotelAvailabilityObserver;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="room_capacity")
    private int roomCapacity;

    @Column(name="price")
    private double price;

    @Column(name="availability")
    private boolean availability;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }




    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }




    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room(int roomCapacity, double price, boolean availability, Hotel hotel) {

        this.roomCapacity = roomCapacity;
        this.price = price;
        this.availability = availability;

        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomCapacity=" + roomCapacity +
                ", price=" + price +
                ", availability=" + availability +

                '}';
    }

    public void setDates(Date dates) {

    }

    @Transient
    private List<HotelAvailabilityObserver> observers = new ArrayList<>();

    public void addObserver(HotelAvailabilityObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(HotelAvailabilityObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Hotel hotel) {
        for (HotelAvailabilityObserver observer : observers) {
            observer.update(hotel);
        }
    }
}

