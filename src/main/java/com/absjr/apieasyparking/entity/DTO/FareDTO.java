package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.Fare;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
public
abstract class FareDTO {

    private Long id;
    private BigDecimal valueFare;
    private BigDecimal additionalValue;
    private BigDecimal overnight;
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
        BeanUtils.copyProperties(entity, this);
    }

    public
    Duration toDuration(LocalTime localTime) {
        return Duration.ofMinutes(localTime.getHour() * 60 + localTime.getMinute());
    }

    public Duration getMinimumStayDuration() {
        return toDuration(minimumStay);
    }

    public Duration getAdditionalStayDuration() {
        return toDuration(additionalStay);
    }
}
