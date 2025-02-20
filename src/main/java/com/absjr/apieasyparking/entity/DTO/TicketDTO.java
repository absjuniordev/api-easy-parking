package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class TicketDTO {

    private Long id;
    private String ticketCode;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private BigDecimal amountPaid;
    private PaymentBox operatorName;
    private LicensePlate licensePlate;

    public TicketDTO(Ticket entity) {
        this.id = entity.getId();
        this.ticketCode = entity.getTicketCode();
        this.entryTime = entity.getEntryTime();
        this.departureTime = entity.getDepartureTime();
        this.amountPaid = entity.getAmountPaid();
        this.licensePlate = entity.getLicensePlate();
        this.operatorName = entity.getPaymentBox() ;
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

    public PaymentBox getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(PaymentBox operatorName) {
        this.operatorName = operatorName;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }
}
