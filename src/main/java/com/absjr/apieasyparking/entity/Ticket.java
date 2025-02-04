package com.absjr.apieasyparking.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant entryTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant departureTime;
    private Double amountPaid;

    @ManyToOne
    @JoinColumn(name = "license_plate_id")
    private LicensePlate licensePlate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_box_id")
    private PaymentBox paymentBox;


    public Ticket() {
    }

    public Ticket(String ticketCode, Instant entryTime, Instant departureTime, Double amountPaid, LicensePlate licensePlate, PaymentBox paymentBox) {
        this.ticketCode = ticketCode;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
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

    public Instant getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
    }

    public Instant getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Instant departureTime) {
        this.departureTime = departureTime;
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
