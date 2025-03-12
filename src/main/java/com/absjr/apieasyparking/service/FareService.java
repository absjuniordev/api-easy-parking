package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.FareDTO;
import com.absjr.apieasyparking.entity.Fare;
import com.absjr.apieasyparking.exeption.FareNotFoundException;
import com.absjr.apieasyparking.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public  class FareService {

    @Autowired
    FareRepository fareRepository;

    public BigDecimal calculateFare(Duration duration, LocalDateTime entryTime, LocalDateTime departureTime) {

            Optional<Fare> optionalFare = fareRepository.findFirstFare();

        if (optionalFare.isEmpty()) {
            throw new FareNotFoundException("Fare not found");
        }

        Fare fare = optionalFare.get();
        FareDTO fareDTO = new FareDTO(fare);
        long additionalTime = duration.toMinutes() - fareDTO.getMinimumStayDuration().toMinutes();
        long totalOvernight = calculateOvernight(entryTime, departureTime, fareDTO);

        return calculateFinalCarPrice(fare, fareDTO, additionalTime, totalOvernight);
    }

    private long calculateOvernight(LocalDateTime entryTime, LocalDateTime departureTime, FareDTO fareDTO) {
        long totalOvernight = 0;

        for (LocalDateTime day = entryTime.toLocalDate().atStartOfDay(); !day.isAfter(departureTime); day = day.plusDays(1)) {
            LocalDateTime startOvernight = day.with(LocalTime.of(0, 0));
            LocalDateTime endOvernight = day.with(LocalTime.of(6, 0));

            if (departureTime.isAfter(startOvernight) && departureTime.isBefore(endOvernight)) {
                totalOvernight++;
            }
        }

        return totalOvernight;
    }



    private BigDecimal calculateFinalCarPrice(Fare fare, FareDTO fareDTO, long additionalTime, long totalOvernight) {
        BigDecimal finalPrice;

        if (additionalTime <= 0) {
            finalPrice = fare.getValueCarFare();
        } else {

            long extraStayHours = (additionalTime + 59) / 60;
            BigDecimal totalAdditionalCharge = fareDTO.getAdditionalCarValue().multiply(BigDecimal.valueOf(extraStayHours));


            finalPrice = fareDTO.getValueCarFare().add(totalAdditionalCharge);


            if (totalOvernight > 0) {
                finalPrice = finalPrice.add(fareDTO.getOvernightCar().multiply(BigDecimal.valueOf(totalOvernight)));
            }
        }
        return finalPrice;
    }

}
