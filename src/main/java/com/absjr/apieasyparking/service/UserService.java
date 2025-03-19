package com.absjr.apieasyparking.service;

import com.absjr.apieasyparking.entity.DTO.UserDTO;
import com.absjr.apieasyparking.entity.User;
import com.absjr.apieasyparking.exeption.UserAlreadyExistsException;
import com.absjr.apieasyparking.exeption.UserNotFoundException;
import com.absjr.apieasyparking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO createUser(String name, String password) {

        User existingUser = userRepository.findByName(name);
        if (existingUser != null){
             throw new UserAlreadyExistsException("User with this name already exists");
        }
        User newUser = new User(name, password);
        return new UserDTO(userRepository.save(newUser));
    }


}
