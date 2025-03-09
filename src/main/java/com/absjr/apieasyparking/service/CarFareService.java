package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.repository.CarFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CarFareService extends BaseFareService<CarFare, CarFareDTO> {

    @Autowired
    private CarFareRepository carFareRepository;

    @Override
    protected CarFare findFare() {
        return carFareRepository.findFirstCarFare().orElse(null);
    }

    @Override
    protected CarFareDTO createFareDTO(CarFare fare) {
        return new CarFareDTO(fare);
    }

    public CarFareDTO createCarFare(CarFareDTO carFareDTO) {
        CarFare carFare = new CarFare(
                carFareDTO.getValueFare(),
                carFareDTO.getAdditionalValue(),
                carFareDTO.getOvernight(),
                carFareDTO.getMinimumStay(),
                carFareDTO.getAdditionalStay(),
                carFareDTO.getStartOvernight(),
                carFareDTO.getEndOvernight()
        );

        carFareRepository.save(carFare);
        return new CarFareDTO(carFare);
    }

    public
    BigDecimal calculateCarFare(Duration duration, LocalDateTime entryTime, LocalDateTime departureTime) {
        return calculateFare(duration, entryTime, departureTime);
    }
}
