package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;
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
import java.time.Instant;
import java.time.LocalDateTime;
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

    @Transactional()
    public
    void payment(String plate) {
        LicensePlate existingPlate = licensePlateRepository.findByPlate(plate);

        if (existingPlate == null) throw new LicensePlateNotFoundException("Plate " + plate + " not found");

        Ticket latestTicket = existingPlate.getTickets().stream()
                .max(Comparator.comparing(Ticket::getEntryTime))
                .orElse(null);

        if (latestTicket == null) throw new TicketNotFoundException("Ticket not found");
        if (latestTicket.getDepartureTime() != null) throw new PaymentBoxException("Payment ok");

        LocalDateTime departureTime = LocalDateTime.now();
        Duration duration = Duration.between(latestTicket.getEntryTime(), departureTime);
        BigDecimal value = getFare(duration);

        latestTicket.setDepartureTime(departureTime);
        latestTicket.setAmountPaid(value);
        ticketRepository.save(latestTicket);
    }



    private static BigDecimal getFare(Duration duration) {
        BigDecimal value;
        if (duration.toHours() <= 3) {
            value = new BigDecimal("5.00");
        } else if (duration.toHours() <= 6) {
            value = new BigDecimal("7.00");
        } else {
            value = new BigDecimal("10.00");
        }
        return value;
    }


}
