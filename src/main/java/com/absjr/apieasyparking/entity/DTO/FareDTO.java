package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.Fare;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
public
class FareDTO {

    private Long id;
    private BigDecimal valueCarFare;
    private BigDecimal valueBikeFare;
    private BigDecimal additionalCarValue;
    private BigDecimal additionalBikeValue;
    private BigDecimal overnightCar;
    private BigDecimal overnightBike;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime withdrawalTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime minimumStay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime additionalStay;
    private Integer startOvernight;
    private Integer endOvernight;

    public
    FareDTO() {
    }

    public
    FareDTO(Fare entity) {
        this.id = getId();
        this.valueCarFare = entity.getValueCarFare();
        this.valueBikeFare = entity.getValueBikeFare();
        this.additionalCarValue = entity.getAdditionalCarValue();
        this.additionalBikeValue = entity.getAdditionalBikeValue();
        this.overnightCar = entity.getOvernightCar();
        this.overnightBike = entity.getOvernightBike();
        this.withdrawalTime = entity.getWithdrawalTime();
        this.minimumStay = entity.getMinimumStay();
        this.additionalStay = entity.getAdditionalStay();
        this.startOvernight = entity.getStartOvernight();
        this.endOvernight = entity.getEndOvernight();
    }

    public
    Duration toDuration(LocalTime localTime) {
        return Duration.ofMinutes(localTime.getHour() * 60 + localTime.getMinute());
    }

    public
    Duration getMinimumStayDuration() {
        return toDuration(minimumStay);
    }

    public
    Duration getAdditionalStayDuration() {
        return toDuration(additionalStay);
    }
}
