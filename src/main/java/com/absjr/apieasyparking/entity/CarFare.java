package com.absjr.apieasyparking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "tb_car_fare")
public class CarFare extends Fare {

    public CarFare() {
    }

    public CarFare(BigDecimal valueFare, BigDecimal additionalValue, BigDecimal overnight,
                   LocalTime minimumStay, LocalTime additionalStay, Integer startOvernight, Integer endOvernight) {
        super(valueFare, additionalValue, overnight, minimumStay, additionalStay, startOvernight, endOvernight);
    }
}
