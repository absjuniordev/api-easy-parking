package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LicensePlateRepository extends JpaRepository<LicensePlate, String> {
    LicensePlate findByPlate(String plate);

    @Query("SELECT DISTINCT l FROM LicensePlate l " +
            "JOIN l.tickets t " +
            "WHERE CAST(t.entryTime AS DATE) = CURRENT_DATE")
    List<LicensePlate> findLicensePlateForToday();

    @Query("SELECT DISTINCT l FROM LicensePlate l " +
            "JOIN l.tickets t " +
            "WHERE t.entryTime BETWEEN :startDate AND :endDate")
    List<LicensePlate> findLicensePlateByEntryTimeBetween(@Param("startDate") LocalDateTime startDate,
                                                          @Param("endDate") LocalDateTime endDate);

    @Query("SELECT DISTINCT l FROM LicensePlate l " +
            "JOIN l.tickets t " +
            "WHERE t.paid = :paid AND CAST(t.entryTime AS DATE) = CURRENT_DATE")
    List<LicensePlate> findLicensePlatesByPaidStatusAndDate(@Param("paid") Boolean paid);




}
