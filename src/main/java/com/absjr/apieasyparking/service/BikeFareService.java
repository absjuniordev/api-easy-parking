package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.BikeFareDTO;
import com.absjr.apieasyparking.entity.BikeFare;
import com.absjr.apieasyparking.exeption.FareNotFoundException;
import com.absjr.apieasyparking.repository.BikeFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                bikeFareDTO.getOvernight(),
                bikeFareDTO.getMinimumStay(),
                bikeFareDTO.getAdditionalStay(),
                bikeFareDTO.getStartOvernight(),
                bikeFareDTO.getEndOvernight()
        );

        bikeFareRepository.save(bikeFare);
        return new BikeFareDTO(bikeFare);
    }

    public BigDecimal calculateBikeFare(Duration duration, LocalDateTime entryTime, LocalDateTime departureTime){

        Optional<BikeFare> optionalBikeFare = bikeFareRepository.findFirstBikeFare();

        if (optionalBikeFare.isEmpty()) {
            throw new FareNotFoundException("Fare don't created");
        }

        BigDecimal finalPrice;
        BikeFare bikeFare = optionalBikeFare.get();
        BikeFareDTO bikeFareDTO = new BikeFareDTO(bikeFare);
        long additionalTime =  duration.toMinutes() - bikeFareDTO.getMinimumStayDuration().toMinutes();
        long totalOvernight = 0;

        for (LocalDateTime day = entryTime.toLocalDate().atStartOfDay(); !day.isAfter(departureTime); day = day.plusDays(1)) {

            LocalDateTime startOvernight = day.with(LocalTime.of(0, 0));
            LocalDateTime endOvernight = day.with(LocalTime.of(6, 0));

            if (departureTime.isAfter(startOvernight) && departureTime.isBefore(endOvernight)) {
                totalOvernight++;
            }
        }

        if (duration.toMinutes() <= bikeFareDTO.getMinimumStayDuration().toMinutes()) {
            finalPrice = bikeFare.getValueFare();
        } else {
            long extraStayHours = (additionalTime + 59) / 60;
            BigDecimal totalAdditionalCharge = bikeFareDTO.getAdditionalValue().multiply(BigDecimal.valueOf(extraStayHours));

            finalPrice = bikeFareDTO.getValueFare().add(totalAdditionalCharge);

            if (totalOvernight >= 1) {
                finalPrice = finalPrice.add(bikeFareDTO.getOvernight().multiply(BigDecimal.valueOf(totalOvernight)));
            }
        }
        return finalPrice;
    }

}
