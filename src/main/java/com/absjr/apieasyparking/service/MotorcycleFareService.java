package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.entity.DTO.MotorcycleFareDTO;
import com.absjr.apieasyparking.entity.MotorcycleFare;
import com.absjr.apieasyparking.repository.MotorcycleFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorcycleFareService {

    @Autowired
    private
    MotorcycleFareRepository motorcycleFareRepository;

    public
    MotorcycleFareDTO createCarFare(MotorcycleFareDTO motorcycleFareDTO) {
        MotorcycleFare motorcycleFare = new MotorcycleFare(
                motorcycleFareDTO.getValueFare(),
                motorcycleFareDTO.getAdditionalValue(),
                motorcycleFareDTO.getMinimumStay(),
                motorcycleFareDTO.getAdditionalStay()
        );

        motorcycleFareRepository.save(motorcycleFare);
        return new MotorcycleFareDTO(motorcycleFare);
    }
}
