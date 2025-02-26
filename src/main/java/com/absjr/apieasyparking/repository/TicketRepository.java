package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
 Ticket findByTicketCode(String ticket);

 @Query("SELECT t FROM Ticket t WHERE FUNCTION('DATE', t.entryTime) = CURRENT_DATE")
 List<Ticket> findTicketsForToday();

// @Query("SELECT t FROM Ticket t WHERE t.paid = :paid AND FUNCTION('DATE', t.entryTime) = CURRENT_DATE")
// List<Ticket> findTicketsByPaidStatusAndDate(@Param("paid") Boolean paid);

 @Query("SELECT t FROM Ticket t WHERE t.paid = :paid AND CAST(t.entryTime AS DATE) = CURRENT_DATE")
 List<Ticket> findTicketsByPaidStatusAndDate(@Param("paid") Boolean paid);


 @Query("SELECT t FROM Ticket t WHERE t.entryTime BETWEEN :startDate AND :endDate")
 List<Ticket> findTicketsByEntryTimeBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
