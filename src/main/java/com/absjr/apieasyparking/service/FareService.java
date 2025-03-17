package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.FareDTO;
import com.absjr.apieasyparking.entity.Fare;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.exeption.FareNotFoundException;
import com.absjr.apieasyparking.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public
class FareService {

    @Autowired
    FareRepository fareRepository;

    public FareDTO createFare(FareDTO fareDTO){
        Fare fare = new Fare(

                fareDTO.getValueCarFare(),
                fareDTO.getValueBikeFare(),
                fareDTO.getAdditionalCarValue(),
                fareDTO.getAdditionalBikeValue(),
                fareDTO.getOvernightCar(),
                fareDTO.getOvernightBike(),
                fareDTO.getMinimumStay(),
                fareDTO.getWithdrawalTime(),
                fareDTO.getMinimumStay(),
                fareDTO.getStartOvernight(),
                fareDTO.getEndOvernight()
        );
        fareRepository.save(fare);
        return new FareDTO(fare);
    }

    public
    BigDecimal calculateFare(Duration duration, LocalDateTime entryTime, LocalDateTime departureTime, VehicleType vehicleType) {

        Fare optionalFare = fareRepository.findFirstFare()
                .orElseThrow(()-> new FareNotFoundException("Fare not found"));

        FareDTO fareDTO = new FareDTO(optionalFare);
        long additionalTime = duration.toMinutes() - fareDTO.getMinimumStayDuration().toMinutes();
        long totalOvernight = calculateOvernight(entryTime, departureTime, fareDTO);

        if (duration.toMinutes() < fareDTO.getWithdrawalTimeDuration().toMinutes()){
            return BigDecimal.valueOf(0.00);
        }
        else if (vehicleType == VehicleType.CAR) {
            return calculateFinalCarPrice(optionalFare, fareDTO, additionalTime, totalOvernight);
        }
        return calculateFinalBikePrice(optionalFare, fareDTO, additionalTime, totalOvernight);

    }

    private
    long calculateOvernight(LocalDateTime entryTime, LocalDateTime departureTime, FareDTO fareDTO) {
        long totalOvernight = 0;

        for (LocalDateTime day = entryTime.toLocalDate().atStartOfDay(); !day.isAfter(departureTime); day = day.plusDays(1)) {
            LocalDateTime startOvernight = day.with(LocalTime.of(fareDTO.getStartOvernight(), 0));
            LocalDateTime endOvernight = day.with(LocalTime.of(fareDTO.getEndOvernight(), 0));

            if (departureTime.isAfter(startOvernight) && departureTime.isBefore(endOvernight)) {
                totalOvernight++;
            }
        }
        return totalOvernight;
    }


    private
    BigDecimal calculateFinalCarPrice(Fare fare, FareDTO fareDTO, long additionalTime, long totalOvernight) {
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

    private
    BigDecimal calculateFinalBikePrice(Fare fare, FareDTO fareDTO, long additionalTime, long totalOvernight) {
        BigDecimal finalPrice;

        if (additionalTime <= 0) {
            finalPrice = fare.getValueBikeFare();
        } else {

            long extraStayHours = (additionalTime + 59) / 60;
            BigDecimal totalAdditionalCharge = fareDTO.getAdditionalCarValue().multiply(BigDecimal.valueOf(extraStayHours));

            finalPrice = fareDTO.getValueCarFare().add(totalAdditionalCharge);

            if (totalOvernight > 0) {
                finalPrice = finalPrice.add(fareDTO.getValueBikeFare().multiply(BigDecimal.valueOf(totalOvernight)));
            }
        }
        return finalPrice;
    }

}
