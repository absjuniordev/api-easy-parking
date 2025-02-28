package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "tb_car_fare")
public
class CarFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valueFare;
    @Column(nullable = false)
    private BigDecimal additionalValue;
    @Column(nullable = false)
    private BigDecimal overnight;
    @Column(nullable = false)
    private LocalTime minimumStay;
    @Column(nullable = false)
    private LocalTime additionalStay;

    public
    CarFare() {
    }

    public
    CarFare(BigDecimal valueFare, BigDecimal additionalValue,BigDecimal overnight ,LocalTime minimumStay, LocalTime additionalStay) {
        this.valueFare = valueFare;
        this.additionalValue = additionalValue;
        this.minimumStay = minimumStay;
        this.additionalStay = additionalStay;
        this.overnight = overnight;
    }


}
