package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "tb_ticket_sequence ")
public class TicketSequence {
    @Id
    private Long id;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private Long sequencerNumber;
}
