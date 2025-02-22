package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.repository.CarFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarFareService {

    @Autowired
    private CarFareRepository carFareRepository;

    public
    CarFareDTO createCarFare(CarFareDTO carFareDTO){
        CarFare carFare = new CarFare(
                carFareDTO.getValueFare(),
                carFareDTO.getAdditionalValue(),
                carFareDTO.getMinimumStay(),
                carFareDTO.getAdditionalStay()
        );
        carFareRepository.save(carFare);
        return new CarFareDTO(carFare);

    }

}
