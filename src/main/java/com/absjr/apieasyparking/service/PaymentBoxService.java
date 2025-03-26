package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.PaymentBoxDTO;
import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.User;
import com.absjr.apieasyparking.entity.enums.PaymentMethods;
import com.absjr.apieasyparking.exeption.LicensePlateNotFoundException;
import com.absjr.apieasyparking.exeption.PaymentBoxException;
import com.absjr.apieasyparking.exeption.TicketNotFoundException;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import com.absjr.apieasyparking.repository.PaymentBoxRepository;
import com.absjr.apieasyparking.repository.TicketRepository;
import com.absjr.apieasyparking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private
    FareService fareService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentBoxRepository paymentBoxRepository;

    @Transactional
    public
    PaymentBoxDTO createPaymentBox(String name) {
        User existUser = userRepository.findFirstByOrderByIdAsc();

        PaymentBox paymentBox = new PaymentBox(name, new ArrayList<>());
        paymentBox.getUser().add(existUser);

        paymentBoxRepository.save(paymentBox);
        return new PaymentBoxDTO(paymentBox);
    }

    @Transactional()
    public
    TicketDTO payment(String plate, PaymentMethods paymentMethods) {
        Ticket latestTicket = consultPlate(plate);

        PaymentBox paymentBox = paymentBoxRepository.findFirstByOrderByIdAsc();
        paymentBox.setPaymentMethods(paymentMethods);
        paymentBoxRepository.save(paymentBox);

        LocalDateTime departureTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        BigDecimal value = calculateValue(latestTicket, departureTime);

        latestTicket.setPaid(true);
        latestTicket.setDepartureTime(departureTime);
        latestTicket.setAmountPaid(value);
        ticketRepository.save(latestTicket);

        return new TicketDTO(latestTicket);
    }

    public
    String timePermanence(String plate) {
        Ticket latestTicket = consultPlate(plate);

        LocalDateTime departureTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Duration duration = Duration.between(latestTicket.getEntryTime(), departureTime);

        BigDecimal value = calculateValue(latestTicket, departureTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return String.format("%02d:%02d  %nR$ %.2f", hours, minutes, value);

    }

    private
    Ticket consultPlate(String plate) {
        LicensePlate existingPlate = licensePlateRepository.findByPlate(plate);

        if (existingPlate == null) throw new LicensePlateNotFoundException("Plate " + plate + " not found");

        Ticket latestTicket = existingPlate.getTickets().stream()
                .max(Comparator.comparing(Ticket::getEntryTime))
                .orElse(null);

        if (latestTicket == null) throw new TicketNotFoundException("Ticket not found");
        if (latestTicket.getDepartureTime() != null) throw new PaymentBoxException("Payment ok");

        return latestTicket;
    }

    private
    BigDecimal calculateValue(Ticket ticket, LocalDateTime departureTime) {
        Duration duration = Duration.between(ticket.getEntryTime(), departureTime);
        return fareService.calculateFare(duration, ticket.getEntryTime(),
                departureTime, ticket.getLicensePlate().getVehicleType());
    }

}

