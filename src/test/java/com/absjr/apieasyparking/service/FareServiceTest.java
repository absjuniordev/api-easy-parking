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
@ExtendWith(MockitoExtension.class)  // Usando o MockitoExtension para inicializar os mocks
class FareServiceTest {

    @InjectMocks
    private FareService fareService;

    @Mock
    private FareRepository fareRepository;

    @Test
    void calculateFare() {

        // Criando uma instância mockada de Fare com valores personalizados
        Fare fare = new Fare();
        fare.setValueCarFare(BigDecimal.valueOf(10)); // Valor base para carro
        fare.setValueBikeFare(BigDecimal.valueOf(7)); // Valor base para moto
        fare.setAdditionalCarValue(BigDecimal.valueOf(1)); // Valor adicional para carro
        fare.setAdditionalBikeValue(BigDecimal.valueOf(1)); // Valor adicional para moto
        fare.setOvernightCar(BigDecimal.valueOf(15)); // Valor noturno para carro
        fare.setOvernightBike(BigDecimal.valueOf(10)); // Valor noturno para moto
        fare.setMinimumStay(LocalTime.of(3, 0)); // Tempo mínimo de estadia
        fare.setWithdrawalTime(LocalTime.of(0, 10)); // Tempo de retirada
        fare.setAdditionalStay(LocalTime.of(1, 0)); // Tempo adicional de estadia
        fare.setStartOvernight(1); // Início da tarifa noturna
        fare.setEndOvernight(6); // Fim da tarifa noturna

        // Mockando o comportamento do fareRepository para retornar o fare mockado
        when(fareRepository.findFirstFare()).thenReturn(Optional.of(fare));

        // Criando os parâmetros de teste
        LocalDateTime entryTime = LocalDateTime.parse("2025-03-28T08:00:00");
        LocalDateTime departureTime = LocalDateTime.parse("2025-03-30T08:00:00");
        Duration duration = Duration.between(entryTime, departureTime); // Estadia
        VehicleType vehicleType = VehicleType.CAR;

        System.out.println(duration.toHours() + " hours");

        // Chama o metodo de calculo de tarifa
        BigDecimal fareValue = fareService.calculateFare(duration, entryTime, departureTime, vehicleType);

        // Verifica se o valor calculado é o esperado
        BigDecimal expectedFare = BigDecimal.valueOf(10) // Valor base para carro
                .add(BigDecimal.valueOf(45)) // Valor adicional para carro
                .add(BigDecimal.valueOf(30)); // Valor noturno para carro (considerando que a estadia é durante a noite)

        // Verifica se o valor retornado é igual ao esperado
        assertNotNull(fareValue);
        assertEquals(expectedFare, fareValue);
        System.out.println("Value R$" + String.format("%.2f", fareValue));
        System.out.println("Expected Value R$" + String.format("%.2f", expectedFare));

        
    }
}
