package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.entity.DTO.MotorcycleFareDTO;
import com.absjr.apieasyparking.entity.MotorcycleFare;
import com.absjr.apieasyparking.repository.MotorcycleFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;

@Service
public class MotorcycleFareService {

    @Autowired
    private
    MotorcycleFareRepository motorcycleFareRepository;

    public
    MotorcycleFareDTO createMotorcycleFare(MotorcycleFareDTO motorcycleFareDTO) {
        MotorcycleFare motorcycleFare = new MotorcycleFare(
                motorcycleFareDTO.getValueFare(),
                motorcycleFareDTO.getAdditionalValue(),
                motorcycleFareDTO.getMinimumStay(),
                motorcycleFareDTO.getAdditionalStay()
        );

        motorcycleFareRepository.save(motorcycleFare);
        return new MotorcycleFareDTO(motorcycleFare);
    }

    public BigDecimal calculateMotorcycleFare(Duration duration){

        Optional<MotorcycleFare> optionalMotorcycleFare = motorcycleFareRepository.findFirstMotorcycleFare();

        if (optionalMotorcycleFare.isEmpty()) {
            throw new RuntimeException("Fare not found");
        }

        BigDecimal finalPrice;
        MotorcycleFare motorcycleFare = optionalMotorcycleFare.get();
        MotorcycleFareDTO motorcycleFareDTO = new MotorcycleFareDTO(motorcycleFare);
        long additionalTime =  duration.toMinutes() - motorcycleFareDTO.getMinimumStayDuration().toMinutes();

        if(duration.toMinutes() <= motorcycleFareDTO.getMinimumStayDuration().toMinutes()){
            finalPrice = motorcycleFare.getValueFare() ;
        }else {
            long extraStay = (additionalTime + 59) / 60;
            BigDecimal additionalValue = motorcycleFareDTO.getAdditionalValue().multiply(BigDecimal.valueOf(extraStay));
            finalPrice = motorcycleFareDTO.getValueFare().add(additionalValue);
        }

        return finalPrice;
    }

}
