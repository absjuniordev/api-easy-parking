package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = ("tb_fare"))
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private BigDecimal valueFare;

    private BigDecimal additionalValue;

    public Fare() {
    }

    public Fare(BigDecimal valueFare, BigDecimal additionalValue) {
        this.valueFare = valueFare;
        this.additionalValue = additionalValue;
    }

    public BigDecimal getValueFare() {
        return valueFare;
    }

    public void setValueFare(BigDecimal valueFare) {
        this.valueFare = valueFare;
    }

    public BigDecimal getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(BigDecimal additionalValue) {
        this.additionalValue = additionalValue;
    }
}
