package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.FareDTO;
import com.absjr.apieasyparking.entity.Fare;
import com.absjr.apieasyparking.exeption.FareNotFoundException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class BaseFareService<T extends Fare, D extends FareDTO> {

    protected abstract T findFare();

    protected abstract D createFareDTO(T fare);

    public BigDecimal calculateFare(Duration duration, LocalDateTime entryTime, LocalDateTime departureTime) {

        T fare = findFare();

        if (fare == null) {
            throw new FareNotFoundException("Fare not found");
        }

        D fareDTO = createFareDTO(fare);
        long additionalTime = duration.toMinutes() - fareDTO.getMinimumStayDuration().toMinutes();
        long totalOvernight = calculateOvernight(entryTime, departureTime, fareDTO);

        return calculateFinalPrice(fare, fareDTO, additionalTime, totalOvernight);
    }

    private long calculateOvernight(LocalDateTime entryTime, LocalDateTime departureTime, D fareDTO) {
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

    private BigDecimal calculateFinalPrice(T fare, D fareDTO, long additionalTime, long totalOvernight) {
        BigDecimal finalPrice;

        if (additionalTime <= 0) {
            finalPrice = fare.getValueFare();
        } else {

            long extraStayHours = (additionalTime + 59) / 60;
            BigDecimal totalAdditionalCharge = fareDTO.getAdditionalValue().multiply(BigDecimal.valueOf(extraStayHours));


            finalPrice = fareDTO.getValueFare().add(totalAdditionalCharge);


            if (totalOvernight > 0) {
                finalPrice = finalPrice.add(fareDTO.getOvernight().multiply(BigDecimal.valueOf(totalOvernight)));
            }
        }

        return finalPrice;
    }

}
