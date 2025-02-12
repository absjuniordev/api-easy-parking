package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.repository.PaymentBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public
class PaymentBoxService {

    @Autowired
    private PaymentBoxRepository paymentBoxRepository;

    @Autowired
    private TicketService ticketService;

    @Transactional
    public
    Ticket createAccess(String plate, String vehicleType, String operatorName) {

        PaymentBox paymentBox = paymentBoxRepository.findByOperatorName(operatorName);

        if (paymentBox == null) {
            paymentBox = new PaymentBox(operatorName, new ArrayList<>());
            paymentBox = paymentBoxRepository.save(paymentBox);
        }

        return ticketService.createTicket(plate, vehicleType, paymentBox);
    }


}
