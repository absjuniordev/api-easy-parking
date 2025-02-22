package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.MotorcycleFare;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalTime;

public
class MotorcycleFareDTO {



    private Long id;
    private BigDecimal valueFare;
    private BigDecimal additionalValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime minimumStay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime additionalStay;

    public
    MotorcycleFareDTO() {
    }

    public
    MotorcycleFareDTO(MotorcycleFare entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public
    Long getId() {
        return id;
    }

    public
    void setId(Long id) {
        this.id = id;
    }

    public
    BigDecimal getValueFare() {
        return valueFare;
    }

    public
    void setValueFare(BigDecimal valueFare) {
        this.valueFare = valueFare;
    }

    public
    BigDecimal getAdditionalValue() {
        return additionalValue;
    }

    public
    void setAdditionalValue(BigDecimal additionalValue) {
        this.additionalValue = additionalValue;
    }

    public
    LocalTime getMinimumStay() {
        return minimumStay;
    }

    public
    void setMinimumStay(LocalTime minimumStay) {
        this.minimumStay = minimumStay;
    }

    public
    LocalTime getAdditionalStay() {
        return additionalStay;
    }

    public
    void setAdditionalStay(LocalTime additionalStay) {
        this.additionalStay = additionalStay;
    }
}
