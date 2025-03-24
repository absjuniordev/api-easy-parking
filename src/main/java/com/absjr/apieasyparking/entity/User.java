package com.absjr.apieasyparking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String password;

    @ManyToOne
    @JoinColumn(name = "payment_box_id")
    private PaymentBox paymentBox;

    public
    User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
