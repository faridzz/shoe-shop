package com.fz.onlineshoestore.controller;

import com.fz.onlineshoestore.model.UserObj;
import com.fz.onlineshoestore.model.dto.UserDto;
import com.fz.onlineshoestore.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    RegistrationService registrationService;
    PasswordEncoder passwordEncoder;

    public RegistrationController(RegistrationService registrationService, PasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public String registerRegistration(@Valid UserObj userObj, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ("value in " + bindingResult.getFieldError().getField() +" is not a valid");
        } else {
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            return registrationService.registration(userObj);
        }


    }
}
