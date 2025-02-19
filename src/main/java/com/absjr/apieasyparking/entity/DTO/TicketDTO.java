package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.Ticket;

import java.math.BigDecimal;
import java.time.Instant;

public class TicketDTO {

    private String ticketCode;
    private Instant entryTime;
    private Instant departureTime;
    private BigDecimal amountPaid;
    private String operatorName;
    private LicensePlate licensePlate;

    public TicketDTO() {
    }

    public
    LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public
    void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public TicketDTO(Ticket ticket) {
        this.ticketCode = ticket.getTicketCode();
        this.entryTime = ticket.getEntryTime();
        this.departureTime = ticket.getDepartureTime();
        this.amountPaid = ticket.getAmountPaid();
        this.licensePlate = ticket.getLicensePlate();
        this.operatorName = ticket.getPaymentBox() != null ? ticket.getPaymentBox().getOperatorName() : null;
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

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }



}
