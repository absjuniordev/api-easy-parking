package com.absjr.apieasyparking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "license_plate_id")
    private LicensePlate licensePlate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_box_id")
    private PaymentBox paymentBox;


    public Ticket() {
    }

    public Ticket(String ticketCode, LocalDateTime entryTime, LocalDateTime departureTime, BigDecimal amountPaid, LicensePlate licensePlate, PaymentBox paymentBox) {
        this.ticketCode = ticketCode;
        this.entryTime =  entryTime.truncatedTo(ChronoUnit.SECONDS);
        this.departureTime = departureTime ;
        this.amountPaid = amountPaid;
        this.licensePlate = licensePlate;
        this.paymentBox = paymentBox;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public PaymentBox getPaymentBox() {
        return paymentBox;
    }

    public void setPaymentBox(PaymentBox paymentBox) {
        this.paymentBox = paymentBox;
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
