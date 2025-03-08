package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.DTO.CarFareDTO;
import com.absjr.apieasyparking.exeption.FareNotFoundException;
import com.absjr.apieasyparking.repository.CarFareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        Optional<CarFare> optionalCarFare = carFareRepository.findFirstCarFare();

        if (optionalCarFare.isEmpty()) {
            throw new FareNotFoundException("Fare not found");
        }

        BigDecimal finalPrice;
        CarFare carFare = optionalCarFare.get();
        CarFareDTO carFareDTO = new CarFareDTO(carFare);
        long additionalTime = duration.toMinutes() - carFareDTO.getMinimumStayDuration().toMinutes();
        long totalOvernight = 0;

        for (LocalDateTime day = entryTime.toLocalDate().atStartOfDay(); !day.isAfter(departureTime); day = day.plusDays(1)) {

            LocalDateTime startOvernight = day.with(LocalTime.of(carFareDTO.getStartOvernight(), 0));
            LocalDateTime endOvernight = day.with(LocalTime.of(carFareDTO.getEndOvernight(), 0));

            if (departureTime.isAfter(startOvernight) && departureTime.isBefore(endOvernight)) {
                totalOvernight++;
            }
        }

        if (duration.toMinutes() <= carFareDTO.getMinimumStayDuration().toMinutes()) {
            finalPrice = carFare.getValueFare();
        } else {
            long extraStayHours = (additionalTime + 59) / 60;
            BigDecimal totalAdditionalCharge = carFareDTO.getAdditionalValue().multiply(BigDecimal.valueOf(extraStayHours));

            finalPrice = carFareDTO.getValueFare().add(totalAdditionalCharge);

            if (totalOvernight >= 1) {
                finalPrice = finalPrice.add(carFareDTO.getOvernight().multiply(BigDecimal.valueOf(totalOvernight)));
            }
        }
        return finalPrice;
    }


}
