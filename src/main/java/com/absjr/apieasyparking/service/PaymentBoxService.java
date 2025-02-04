package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import com.absjr.apieasyparking.repository.PaymentBoxRepository;
import com.absjr.apieasyparking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

import static java.util.Arrays.stream;

@Service
public class PaymentBoxService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private LicensePlateRepository licensePlateRepository;
    @Autowired
    private PaymentBoxRepository paymentBoxRepository;

    @Transactional(readOnly = true)
    public Ticket creteAcess(String plate, String vehicleType, String operatorName) {

        LicensePlate existingPlate = licensePlateRepository.findByPlate(plate);
        if (existingPlate == null) {
            VehicleType newVehicle = VehicleType.valueOf(vehicleType.toUpperCase());
            existingPlate = new LicensePlate(plate, newVehicle);
            existingPlate = licensePlateRepository.save(existingPlate);
        }

//        VehicleType newVehicle = VehicleType.valueOf(vehicleType.toUpperCase());
//        LicensePlate newPlate = new LicensePlate(plate, newVehicle);
//        newPlate = licensePlateRepository.save(newPlate);


        PaymentBox paymentBox = new PaymentBox();
        paymentBox.setOperatorName(operatorName);
        paymentBox = paymentBoxRepository.save(paymentBox);

        String ticketCode = randomCode();
        Ticket ticket = new Ticket();
        ticket.setTicketCode(ticketCode);
        ticket.setEntryTime(Instant.now());
        ticket.setLicensePlate(existingPlate);
        ticket.setPaymentBox(paymentBox);

        existingPlate.getTickets().add(ticket);
        licensePlateRepository.save(existingPlate);

        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public LicensePlate getPlate(String plate){
      LicensePlate licensePlate =  licensePlateRepository.findByPlate(plate);

        return licensePlate;
    }

    private String randomCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
