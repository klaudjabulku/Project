package Entities;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="reservations")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_id")
    private int roomId;
    // todo one to many
    @Column(name = "customer_id")

    private int customerId;
    @Column(name = "start_date")

    private LocalDate startDate;
    @Column(name = "end_date")

    private LocalDate endDate;
    @Column(name = "total_price")

    private double totalPrice;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }




    public Reservations() {
    }

    public Reservations(int roomId, int customerId, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.roomId = roomId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                ", roomId=" + roomId +
                ", customerId=" + customerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +

                '}';
    }
}
