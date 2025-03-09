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
public class CarFareDTO extends FareDTO {

    public CarFareDTO(){}
    public CarFareDTO(CarFare entity) {
        BeanUtils.copyProperties(entity, this);
    }


}
