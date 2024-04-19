package Entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="paypal")
    private String paypal;
    @Column(name="credit_card")
    private String creditCard;
    @Column(name="coupon_code")
    private  String couponCode;
    public Payment(int i, double totalPrice, String paymentMethod){}

    public Payment(Integer id, String paypal, String creditCard, String couponCode) {
        this.id = id;
        this.paypal = paypal;
        this.creditCard = creditCard;
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paypal='" + paypal + '\'' +
                ", creditCardNumber='" + creditCard + '\'' +
                ", couponCode='" + couponCode + '\'' +
                '}';
    }
}
