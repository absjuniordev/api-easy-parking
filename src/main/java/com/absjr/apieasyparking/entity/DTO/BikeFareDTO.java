package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.BikeFare;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Setter
@Getter
public
class BikeFareDTO {

    private Long id;
    private BigDecimal valueFare;
    private BigDecimal additionalValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime minimumStay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime additionalStay;

    public
    BikeFareDTO() {
    }

    public
    BikeFareDTO(BikeFare entity) {
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
