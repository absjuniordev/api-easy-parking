package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.Fare;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.repository.FareRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class FareServiceTest {

    @InjectMocks
    private FareService fareService;

    @Mock
    private FareRepository fareRepository;

    @Test
    void calculateFare() {


        Fare fare = new Fare();
        fare.setValueCarFare(BigDecimal.valueOf(10));
        fare.setValueBikeFare(BigDecimal.valueOf(7));
        fare.setAdditionalCarValue(BigDecimal.valueOf(1));
        fare.setAdditionalBikeValue(BigDecimal.valueOf(1));
        fare.setOvernightCar(BigDecimal.valueOf(15));
        fare.setOvernightBike(BigDecimal.valueOf(10));
        fare.setMinimumStay(LocalTime.of(3, 0));
        fare.setWithdrawalTime(LocalTime.of(0, 10));
        fare.setAdditionalStay(LocalTime.of(1, 0));
        fare.setStartOvernight(0);
        fare.setEndOvernight(6);

        //  mock
        when(fareRepository.findFirstFare()).thenReturn(Optional.of(fare));

        LocalDateTime entryTime = LocalDateTime.parse("2025-03-28T08:00:00");
        LocalDateTime departureTime = LocalDateTime.parse("2025-03-30T08:00:00");
        Duration duration = Duration.between(entryTime, departureTime);
        VehicleType vehicleType = VehicleType.CAR;

        BigDecimal fareValue = fareService.calculateFare(duration, entryTime, departureTime, vehicleType);

        long timeWithoutMinimumStay = duration.toHours() >= 4 ? duration.toHours() - fare.getMinimumStay().getHour() : 0;
        long currentFare = fare.getValueCarFare().longValue();
        long totalOvernight = 30;


        BigDecimal expectedFare =
                BigDecimal.valueOf(currentFare)
                        .add(BigDecimal.valueOf(timeWithoutMinimumStay))
                        .add(BigDecimal.valueOf(totalOvernight));

        assertNotNull(fareValue);
        assertEquals(expectedFare, fareValue);

        System.out.println(duration.toHours() + " total hours");
        System.out.println(timeWithoutMinimumStay + " hours stay");
        System.out.println("Value R$" + String.format("%.2f", fareValue));
        System.out.println("Expected Value R$" + String.format("%.2f", expectedFare));


    }
}
