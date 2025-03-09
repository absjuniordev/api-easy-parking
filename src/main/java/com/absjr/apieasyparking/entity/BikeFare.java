package com.absjr.apieasyparking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "tb_bike_fare")
public class BikeFare extends Fare {

    public BikeFare() {
    }

    public BikeFare(BigDecimal valueFare, BigDecimal additionalValue, BigDecimal overnight,
                    LocalTime minimumStay, LocalTime additionalStay, Integer startOvernight, Integer endOvernight) {
        super(valueFare, additionalValue, overnight, minimumStay, additionalStay, startOvernight, endOvernight);
    }
}
