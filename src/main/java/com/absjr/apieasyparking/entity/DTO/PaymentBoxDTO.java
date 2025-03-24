package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.PaymentBox;
import com.absjr.apieasyparking.entity.Ticket;
import com.absjr.apieasyparking.entity.User;
import com.absjr.apieasyparking.entity.enums.PaymentMethods;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaymentBoxDTO {


    private Long id;

    private String name;

    private PaymentMethods paymentMethods;

    private List<User> user = new ArrayList<>();

    private List<Ticket> tickets = new ArrayList<>();

    public PaymentBoxDTO(PaymentBox entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
