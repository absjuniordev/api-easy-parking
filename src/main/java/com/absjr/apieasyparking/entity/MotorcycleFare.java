package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;

@Entity
@Table(name = "tb_motorcycle_fare")
public class MotorcycleFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valueFare;
    @Column(nullable = false)
    private BigDecimal additionalValue;
    @Column(nullable = false)
    private Duration minimumStay;
    @Column(nullable = false)
    private Duration additionalStay;

    public MotorcycleFare() {
    }

    public MotorcycleFare(BigDecimal valueFare, BigDecimal additionalValue, Duration minimumStay, Duration additionalStay) {
        this.valueFare = valueFare;
        this.additionalValue = additionalValue;
        this.minimumStay = minimumStay;
        this.additionalStay = additionalStay;
    }


    public BigDecimal getValueFare() {
        return valueFare;
    }

    public void setValueFare( BigDecimal valueFare) {
        this.valueFare = valueFare;
    }

    public BigDecimal getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(BigDecimal additionalValue) {
        this.additionalValue = additionalValue;
    }


    public Duration getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(Duration minimumStay) {
        this.minimumStay = minimumStay;
    }


    public Duration getAdditionalStay() {
        return additionalStay;
    }

    public void setAdditionalStay( Duration additionalStay) {
        this.additionalStay = additionalStay;
    }
}
