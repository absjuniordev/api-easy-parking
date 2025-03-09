package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Setter
@Getter
@MappedSuperclass
public abstract class Fare {

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

    private Integer startOvernight;
    private Integer endOvernight;

    public Fare() {
    }

    public Fare(BigDecimal valueFare, BigDecimal additionalValue,
                BigDecimal overnight, LocalTime minimumStay, LocalTime additionalStay,
                Integer startOvernight, Integer endOvernight) {
        this.valueFare = valueFare;
        this.additionalValue = additionalValue;
        this.overnight = overnight;
        this.minimumStay = minimumStay;
        this.additionalStay = additionalStay;
        this.startOvernight = startOvernight;
        this.endOvernight = endOvernight;
    }
}
