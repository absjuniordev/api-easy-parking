package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.CarFare;
import com.absjr.apieasyparking.entity.MotorcycleFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MotorcycleFareRepository extends JpaRepository<MotorcycleFare,Long > {
    @Query("SELECT cf FROM CarFare cf")
    Optional<MotorcycleFare> findFirstMotorcycleFare();
}
