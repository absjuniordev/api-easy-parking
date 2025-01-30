package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketCode;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private Double amountPaid;

    @ManyToOne
    @JoinColumn(name = "plate_id")
    private LicensePlate licensePlate;

    @ManyToOne
    @JoinColumn(name = "payment_box_id")
    private PaymentBox paymentBox;

    public Ticket() {
    }

    public Ticket(String ticketCode, LocalDateTime departureTime, LocalDateTime entryTime, Double amountPaid, LicensePlate licensePlate, PaymentBox paymentBox) {
        this.ticketCode = ticketCode;
        this.departureTime = departureTime;
        this.entryTime = entryTime;
        this.amountPaid = amountPaid;
        this.licensePlate = licensePlate;
        this.paymentBox = paymentBox;
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

    public PaymentBox getPaymentBox() {
        return paymentBox;
    }

    public void setPaymentBox(PaymentBox paymentBox) {
        this.paymentBox = paymentBox;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
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
