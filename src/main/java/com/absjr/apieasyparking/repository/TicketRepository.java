package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.DTO.TicketDTO;
import com.absjr.apieasyparking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
 //  Ticket findByPlateAndDepartureTimeIsNull(LicensePlate plate);
 Ticket findByTicketCode(String ticket);
}
