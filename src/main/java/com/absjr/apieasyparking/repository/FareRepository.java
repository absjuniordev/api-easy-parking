package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FareRepository extends JpaRepository<Fare, Long> {
    @Query("SELECT cf FROM Fare cf")
    Optional<Fare> findFirstFare();
}
