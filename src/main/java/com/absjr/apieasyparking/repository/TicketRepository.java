package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
 //  Ticket findByPlateAndDepartureTimeIsNull(LicensePlate plate);
}
