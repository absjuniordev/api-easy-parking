package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.BikeFareDTO;
import com.absjr.apieasyparking.entity.BikeFare;
import com.absjr.apieasyparking.repository.BikeFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;

@Service
public class BikeFareService {

    @Autowired
    private
    BikeFareRepository bikeFareRepository;

    public
    BikeFareDTO createBikeFare(BikeFareDTO bikeFareDTO) {
        BikeFare bikeFare = new BikeFare(
                bikeFareDTO.getValueFare(),
                bikeFareDTO.getAdditionalValue(),
                bikeFareDTO.getMinimumStay(),
                bikeFareDTO.getAdditionalStay()
        );

        bikeFareRepository.save(bikeFare);
        return new BikeFareDTO(bikeFare);
    }

    public BigDecimal calculateBikeFare(Duration duration){

        Optional<BikeFare> optionalBikeFare = bikeFareRepository.findFirstBikeFare();

        if (optionalBikeFare.isEmpty()) {
            throw new RuntimeException("Fare not found");
        }

        BigDecimal finalPrice;
        BikeFare bikeFare = optionalBikeFare.get();
        BikeFareDTO bikeFareDTO = new BikeFareDTO(bikeFare);
        long additionalTime =  duration.toMinutes() - bikeFareDTO.getMinimumStayDuration().toMinutes();

        if(duration.toMinutes() <= bikeFareDTO.getMinimumStayDuration().toMinutes()){
            finalPrice = bikeFare.getValueFare() ;
        }else {
            long extraStay = (additionalTime + 59) / 60;
            BigDecimal additionalValue = bikeFareDTO.getAdditionalValue().multiply(BigDecimal.valueOf(extraStay));
            finalPrice = bikeFareDTO.getValueFare().add(additionalValue);
        }
        return finalPrice;
    }

}
