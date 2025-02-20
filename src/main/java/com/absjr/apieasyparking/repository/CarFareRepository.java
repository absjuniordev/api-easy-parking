package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.CarFare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarFareRepository extends JpaRepository<CarFare,Long > {
}
