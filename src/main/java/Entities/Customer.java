package Entities;
import Observer.HotelAvailabilityObserver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="customer")
public class Customer implements HotelAvailabilityObserver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="location")
    private String location;
    @OneToMany
    @JoinTable(name = "customer_payment",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "payment_id", referencedColumnName = "id")})
    private List<Payment> payment;
    public Customer(){

    }

    public Customer(String name, String surname, String email, String phoneNumber, String location, List<Payment> payment) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.payment = payment;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void update(Hotel hotel) {
        System.out.println("Hotel " + hotel.getName() + " is now available for booking!");
    }
}