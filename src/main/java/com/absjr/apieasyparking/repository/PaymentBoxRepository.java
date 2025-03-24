package com.absjr.apieasyparking.repository;

import com.absjr.apieasyparking.entity.PaymentBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface PaymentBoxRepository extends JpaRepository<PaymentBox, Long> {
    PaymentBox findFirstByOrderByIdAsc();
}
