package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.BikeFare;
import org.springframework.beans.BeanUtils;

public class BikeFareDTO extends FareDTO {

    public BikeFareDTO() {
    }

    public BikeFareDTO(BikeFare entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
