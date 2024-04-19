package com.fz.onlineshoestore.service;

import com.fz.onlineshoestore.model.UserObj;
import com.fz.onlineshoestore.model.dto.UserDto;
import com.fz.onlineshoestore.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registration( UserObj userObj) {
        if (userObj != null && userObj.getUsername() != null && userObj.getPassword() != null && userObj.getEmail() != null) {
            userRepository.save(userObj);
            return "added";
        } else {
            return "false";
        }

    }
}
