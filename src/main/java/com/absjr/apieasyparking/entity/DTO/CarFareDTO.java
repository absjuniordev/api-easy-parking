package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.CarFare;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
public class CarFareDTO {

    private Long id;
    private BigDecimal valueFare;
    private BigDecimal additionalValue;
    private BigDecimal overnight;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime minimumStay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime additionalStay;

    public
    CarFareDTO() {
    }

    public CarFareDTO(CarFare entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Duration toDuration(LocalTime localTime) {
        return Duration.ofMinutes(localTime.getHour() * 60 + localTime.getMinute());
    }

    public Duration getMinimumStayDuration() {
        return toDuration(minimumStay);
    }

    public Duration getAdditionalStayDuration() {
        return toDuration(additionalStay);
    }
}
