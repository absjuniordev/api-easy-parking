package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.BikeFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BikeFareRepository extends JpaRepository<BikeFare,Long > {
    @Query("SELECT cf FROM BikeFare cf")
    Optional<BikeFare> findFirstBikeFare();
}
