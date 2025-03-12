package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "tb_car_fare")
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valueCarFare;

    @Column(nullable = false)
    private BigDecimal valueBikeFare;

    @Column(nullable = false)
    private BigDecimal additionalCarValue;

    @Column(nullable = false)
    private BigDecimal additionalBikeValue;

    @Column(nullable = false)
    private BigDecimal overnightCar;

    @Column(nullable = false)
    private BigDecimal overnightBike;

    @Column(nullable = false)
    private LocalTime minimumStay;

    @Column(nullable = false)
    private Duration withdrawal;

    @Column(nullable = false)
    private LocalTime additionalStay;

    private Integer startOvernight;
    private Integer endOvernight;

    public Fare() {
    }

    public Fare(BigDecimal valueCarFare, BigDecimal valueBikeFare, BigDecimal additionalCarValue,
                BigDecimal additionalBikeValue, BigDecimal overnightCar, BigDecimal overnightBike,
                LocalTime minimumStay, Duration withdrawal,
                LocalTime additionalStay, Integer startOvernight, Integer endOvernight) {
        this.valueCarFare = valueCarFare;
        this.valueBikeFare = valueBikeFare;
        this.additionalCarValue = additionalCarValue;
        this.additionalBikeValue = additionalBikeValue;
        this.overnightCar = overnightCar;
        this.overnightBike = overnightBike;
        this.minimumStay = minimumStay;
        this.withdrawal = withdrawal;
        this.additionalStay = additionalStay;
        this.startOvernight = startOvernight;
        this.endOvernight = endOvernight;
    }
}
