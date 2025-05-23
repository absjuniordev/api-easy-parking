package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.LicensePlate;
import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public
class TicketDTO {

    private Long id;
    private String ticketCode;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private BigDecimal amountPaid;
    private PaymentBox paymentBox;
    private LicensePlate licensePlate;
    private Boolean paid;

    public
    TicketDTO(Ticket entity) {
        this.id = entity.getId();
        this.ticketCode = entity.getTicketCode();
        this.entryTime = entity.getEntryTime();
        this.departureTime = entity.getDepartureTime();
        this.amountPaid = entity.getAmountPaid();
        this.licensePlate = entity.getLicensePlate();
        this.paymentBox = entity.getPaymentBox();
        this.paid = entity.getPaid();
    }

}
