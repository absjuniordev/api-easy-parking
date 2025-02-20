package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.MotorcycleFare;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Duration;

public class MotorcycleFareDTO {

    private Long id;
    private BigDecimal valueFare;
    private BigDecimal additionalValue;
    private Duration minimumStay;
    private Duration additionalStay;

    public MotorcycleFareDTO(MotorcycleFare entity) {
        BeanUtils.copyProperties(entity, this);
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

    public Duration getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(Duration minimumStay) {
        this.minimumStay = minimumStay;
    }

    public Duration getAdditionalStay() {
        return additionalStay;
    }

    public void setAdditionalStay(Duration additionalStay) {
        this.additionalStay = additionalStay;
    }
}
