package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Random;

@Service
public
class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private
    LicensePlateService licensePlateService;


    @Transactional(readOnly = true)
    public
    Ticket createTicket(String plate, String vehicleType, PaymentBox paymentBox) {

        LicensePlate existingPlate = licensePlateService.getOrCreateLicensePlate(plate, vehicleType);

        String ticketCode = randomCode();
        Ticket ticket = new Ticket(ticketCode, Instant.now(), null, 0.0, existingPlate, paymentBox);

        ticket = ticketRepository.save(ticket);
        existingPlate.getTickets().add(ticket);

        return ticket;
    }

    private
    String randomCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }


}
