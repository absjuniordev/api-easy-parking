package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.exeption.TicketNotFoundException;
import com.absjr.apieasyparking.repository.PaymentBoxRepository;
import com.absjr.apieasyparking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public
class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private LicensePlateService licensePlateService;

    @Autowired
    private PaymentBoxRepository paymentBoxRepository;


    @Transactional(readOnly = true)
    public
    Ticket createTicket(String plate, String vehicleType, String operatorName) {
        PaymentBox paymentBox = paymentBoxRepository.findByOperatorName(operatorName);

        if (paymentBox == null) {
            paymentBox = new PaymentBox(operatorName, new ArrayList<>());
            paymentBox = paymentBoxRepository.save(paymentBox);
        }

        LicensePlate existingPlate = licensePlateService.getOrCreateLicensePlate(plate, vehicleType);
        String ticketCode = randomCode();
        Ticket ticket = new Ticket(ticketCode, Instant.now(), null, 0.0, existingPlate, paymentBox);

        ticket = ticketRepository.save(ticket);
        existingPlate.getTickets().add(ticket);

        return ticket;
    }

    public
    TicketDTO findByTicket(String ticket) {
        TicketDTO existingTicket = ticketRepository.findByTicketCode(ticket);
        if (existingTicket == null) throw new TicketNotFoundException("The Ticket " + ticket + " does not exist");
        return existingTicket;
    }

    public
    List<String> getAllTicket() {
        List<String> resultSearch = ticketRepository.findAll()
                .stream()
                .map(Ticket::getTicketCode)
                .collect(Collectors.toList());
        if (resultSearch.isEmpty()) throw new TicketNotFoundException("List is empty");
        return resultSearch;
    }

    private
    String randomCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }


}
