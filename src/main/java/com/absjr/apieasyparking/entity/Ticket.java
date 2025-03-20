package com.absjr.apieasyparking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "tb_ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime entryTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureTime;

    private BigDecimal amountPaid;
    private Boolean paid ;

    @ManyToOne
    @JoinColumn(name = "license_plate_id")
    private LicensePlate licensePlate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_box_id")
    private PaymentBox paymentBox;


    public Ticket() {
    }

    public Ticket(String ticketCode, LocalDateTime entryTime, LocalDateTime departureTime, BigDecimal amountPaid, LicensePlate licensePlate, PaymentBox paymentBox, Boolean paid) {
        this.ticketCode = ticketCode;
        this.entryTime =  entryTime.truncatedTo(ChronoUnit.SECONDS);
        this.departureTime = departureTime ;
        this.amountPaid = amountPaid;
        this.licensePlate = licensePlate;
        this.paymentBox = paymentBox;
        this.paid = paid ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(ticketCode, ticket.ticketCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketCode);
    }
}
