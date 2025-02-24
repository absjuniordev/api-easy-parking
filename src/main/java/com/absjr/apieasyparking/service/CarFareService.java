package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.repository.CarFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public
class CarFareService {

    @Autowired
    private CarFareRepository carFareRepository;

    public
    CarFareDTO createCarFare(CarFareDTO carFareDTO) {
        CarFare carFare = new CarFare(
                carFareDTO.getValueFare(),
                carFareDTO.getAdditionalValue(),
                carFareDTO.getMinimumStay(),
                carFareDTO.getAdditionalStay()
        );

        carFareRepository.save(carFare);
        return new CarFareDTO(carFare);
    }

    public BigDecimal calculateCarFare(Duration duration){

        Optional<CarFare> optionalCarFare = carFareRepository.findFirstCarFare();

        if (optionalCarFare.isEmpty()) {
            throw new RuntimeException("Fare not found");
        }

        BigDecimal finalPrice;
        CarFare carFare = optionalCarFare.get();
        CarFareDTO carFareDTO = new CarFareDTO(carFare);
        long additionalTime =  duration.toMinutes() - carFareDTO.getMinimumStayDuration().toMinutes();

        if(duration.toMinutes() <= carFareDTO.getMinimumStayDuration().toMinutes()){
            finalPrice = carFare.getValueFare() ;
        }else {
           long extraStay = (additionalTime + 59) / 60;
            BigDecimal additionalValue = carFareDTO.getAdditionalValue().multiply(BigDecimal.valueOf(extraStay));
            finalPrice = carFareDTO.getValueFare().add(additionalValue);
        }

        return finalPrice;
    }


}
