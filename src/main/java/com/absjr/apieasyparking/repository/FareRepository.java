package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.MotorcycleFare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<MotorcycleFare,Long > {
}
