package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.CarFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarFareRepository extends JpaRepository<CarFare,Long > {

    @Query("SELECT cf FROM CarFare cf")
    Optional<CarFare> findFirstCarFare();
}
