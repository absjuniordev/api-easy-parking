package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.exeption.LicensePlateNotFoundException;
import com.absjr.apieasyparking.exeption.PaymentBoxException;
import com.absjr.apieasyparking.exeption.TicketNotFoundException;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import com.absjr.apieasyparking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;


@Service
public
class PaymentBoxService {

    @Autowired
    private
    TicketRepository ticketRepository;

    @Autowired
    private
    LicensePlateRepository licensePlateRepository;

    @Autowired
    private CarFareService carFareService;

    @Autowired
    private BikeFareService bikeFareService;

    @Transactional()
    public
    TicketDTO payment(String plate) {
        LicensePlate existingPlate = licensePlateRepository.findByPlate(plate);

        if (existingPlate == null) throw new LicensePlateNotFoundException("Plate " + plate + " not found");

        Ticket latestTicket = existingPlate.getTickets().stream()
                .max(Comparator.comparing(Ticket::getEntryTime))
                .orElse(null);

        if (latestTicket == null) throw new TicketNotFoundException("Ticket not found");
        if (latestTicket.getDepartureTime() != null) throw new PaymentBoxException("Payment ok");

        LocalDateTime departureTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Duration duration = Duration.between(latestTicket.getEntryTime(), departureTime);


        BigDecimal value = existingPlate.getVehicleType() == VehicleType.valueOf("CAR") ?
                carFareService.calculateCarFare(duration) :
                bikeFareService.calculateBikeFare(duration);


        latestTicket.setDepartureTime(departureTime);
        latestTicket.setAmountPaid(value);
        ticketRepository.save(latestTicket);

        return new TicketDTO(latestTicket);
    }


}
