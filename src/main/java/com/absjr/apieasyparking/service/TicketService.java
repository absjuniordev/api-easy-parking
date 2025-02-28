package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.TicketSequence;
import com.absjr.apieasyparking.exeption.TicketNotFoundException;
import com.absjr.apieasyparking.repository.PaymentBoxRepository;
import com.absjr.apieasyparking.repository.TicketRepository;
import com.absjr.apieasyparking.repository.TicketSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private TicketSequenceRepository ticketSequenceRepository;

    @Transactional
    public
    Ticket createTicket(String plate, String vehicleType, String operatorName) {
        PaymentBox paymentBox = paymentBoxRepository.findByOperatorName(operatorName);

        if (paymentBox == null) {
            paymentBox = new PaymentBox(operatorName, new ArrayList<>());
            paymentBox = paymentBoxRepository.save(paymentBox);
        }

        String idOperator = String.valueOf(paymentBox.getId());

        LicensePlate existingPlate = licensePlateService.getOrCreateLicensePlate(plate, vehicleType);
        String ticketCode = generateTicketCode();
        Ticket ticket = new Ticket(ticketCode, LocalDateTime.now(), null, null, existingPlate, paymentBox, false);

        ticket = ticketRepository.save(ticket);
        existingPlate.getTickets().add(ticket);

        return ticket;
    }

    @Transactional(readOnly = true)
    public
    TicketDTO findByTicket(String ticket) {
        Ticket existingTicket = ticketRepository.findByTicketCode(ticket);
        if (existingTicket == null) throw new TicketNotFoundException("The Ticket " + ticket + " does not exist");
        return new TicketDTO(existingTicket);
    }

    @Transactional(readOnly = true)
    public
    List<String> findTicketsForToday() {
        List<String> resultSearch = ticketRepository.findTicketsForToday()
                .stream()
                .map(Ticket::getTicketCode)
                .collect(Collectors.toList());
        if (resultSearch.isEmpty()) throw new TicketNotFoundException("List is empty");
        return resultSearch;
    }

    @Transactional(readOnly = true)
    public
    List<String> findTicketsByPaidStatusAndDate(Boolean paid) {
        List<String> resultSearch = ticketRepository.findTicketsByPaidStatusAndDate(paid)
                .stream()
                .map(Ticket::getTicketCode)
                .collect(Collectors.toList());
        if (resultSearch.isEmpty()) throw new TicketNotFoundException("List is empty");
        return resultSearch;
    }

    @Transactional(readOnly = true)
    public
    List<String> findTicketsByEntryTimeBetween(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

        List<String> resultSearch = ticketRepository.findTicketsByEntryTimeBetween(startDateTime, endDateTime)
                .stream()
                .map(Ticket::getTicketCode)
                .collect(Collectors.toList());

        if (resultSearch.isEmpty()) {
            throw new TicketNotFoundException("List is empty");
        }

        return resultSearch;
    }


    //DELETE ALL
    //DELETE BY TICKET CODE

    private String generateTicketCode() {
        String period = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        TicketSequence ticketSequence = ticketSequenceRepository.findByPeriod(period)
                .orElseGet(() -> createNewTicketSequence(period));

        String ticketCode = period + ticketSequence.getSequencerNumber();

        ticketSequence.setSequencerNumber(ticketSequence.getSequencerNumber() + 1);
        ticketSequenceRepository.save(ticketSequence);

        return ticketCode;
    }

    private TicketSequence createNewTicketSequence(String period) {
        TicketSequence newSequence = new TicketSequence();
        newSequence.setId(1L);
        newSequence.setPeriod(period);
        newSequence.setSequencerNumber(1L);
        ticketSequenceRepository.save(newSequence);
        return newSequence;
    }


}
