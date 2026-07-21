package com.codingshuttle.airbnb.airbnb.entity;

import com.codingshuttle.airbnb.airbnb.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToOne(fetch=FetchType.LAZY)
    private Booking booking;
}
