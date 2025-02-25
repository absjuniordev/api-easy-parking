package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "tb_bike_fare")
public class BikeFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valueFare;
    @Column(nullable = false)
    private BigDecimal additionalValue;
    @Column(nullable = false)
    private LocalTime minimumStay;
    @Column(nullable = false)
    private LocalTime additionalStay;

    public
    BikeFare() {
    }

    public
    BikeFare(BigDecimal valueFare, BigDecimal additionalValue, LocalTime minimumStay, LocalTime additionalStay) {
        this.valueFare = valueFare;
        this.additionalValue = additionalValue;
        this.minimumStay = minimumStay;
        this.additionalStay = additionalStay;
    }


}
