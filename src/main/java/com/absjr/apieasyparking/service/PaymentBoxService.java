package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.LicensePlateDTO;
import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.enums.VehicleType;
import com.absjr.apieasyparking.exeption.LicensePlateNotFoundException;
import com.absjr.apieasyparking.repository.LicensePlateRepository;
import com.absjr.apieasyparking.repository.PaymentBoxRepository;
import com.absjr.apieasyparking.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public Ticket createAccess(String plate, String vehicleType, String operatorName) {

        LicensePlate existingPlate = licensePlateRepository.findByPlate(plate);
        if (existingPlate == null) {
            VehicleType newVehicle = VehicleType.valueOf(vehicleType.toUpperCase());
            existingPlate = new LicensePlate(plate, newVehicle);
            existingPlate = licensePlateRepository.save(existingPlate);
        }

        PaymentBox paymentBox = new PaymentBox(operatorName, new ArrayList<>());
        paymentBox = paymentBoxRepository.save(paymentBox);

        String ticketCode = randomCode();
        Ticket ticket = new Ticket(ticketCode, Instant.now(), null, 0.0, existingPlate, paymentBox);

        ticketRepository.save(ticket);  // Salve o ticket primeiro
        existingPlate.getTickets().add(ticket);  // Agora adicione à lista
        licensePlateRepository.save(existingPlate);  // Salve a LicensePlate com o ticket já salvo

        return ticket;
    }

    @Transactional(readOnly = true)
    public
    LicensePlateDTO getPlate(String plate){
      LicensePlate licensePlate = licensePlateRepository.findByPlate(plate);
      if (licensePlate == null){
          throw new LicensePlateNotFoundException("The Plate "+ plate + " is null");
      }
        return new LicensePlateDTO(licensePlate);
    }

    @Transactional
    public void delete(String plate){
       if(!licensePlateRepository.existsById(plate)){
            throw  new LicensePlateNotFoundException("This Plate [" + plate + "] not exist");
        }
        licensePlateRepository.deleteById(plate);
    }

    private String randomCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
