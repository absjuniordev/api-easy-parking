package com.absjr.apieasyparking.entity.DTO;

import com.absjr.apieasyparking.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDTO {

    private Integer id;

    private String name;
    private String password;

    public UserDTO(){}

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
