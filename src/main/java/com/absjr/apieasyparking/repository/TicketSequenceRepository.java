package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.TicketSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketSequenceRepository extends JpaRepository<TicketSequence, Long> {

    Optional<TicketSequence> findByPeriod(String period);
}
