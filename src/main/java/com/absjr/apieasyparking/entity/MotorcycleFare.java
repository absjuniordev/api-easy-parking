package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

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
    private LocalTime minimumStay;
    @Column(nullable = false)
    private LocalTime additionalStay;

    public MotorcycleFare() {
    }

    public MotorcycleFare(BigDecimal valueFare, BigDecimal additionalValue, LocalTime minimumStay, LocalTime additionalStay) {
        this.valueFare = valueFare;
        this.additionalValue = additionalValue;
        this.minimumStay = minimumStay;
        this.additionalStay = additionalStay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public LocalTime getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(LocalTime minimumStay) {
        this.minimumStay = minimumStay;
    }


    public LocalTime getAdditionalStay() {
        return additionalStay;
    }

    public void setAdditionalStay( LocalTime additionalStay) {
        this.additionalStay = additionalStay;
    }
}
