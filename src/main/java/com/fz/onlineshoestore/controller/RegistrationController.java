package com.fz.onlineshoestore.controller;

import com.fz.onlineshoestore.model.UserObj;
import com.fz.onlineshoestore.model.dto.UserDto;
import com.fz.onlineshoestore.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/registrations")
public class RegistrationController {
    RegistrationService registrationService;
    PasswordEncoder passwordEncoder;

    public RegistrationController(RegistrationService registrationService, PasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> registerRegistration(@Valid UserObj userObj, BindingResult bindingResult) {
        if (registrationService.usernameCheck(userObj.getUsername())) {
            return new ResponseEntity<String>("this user name is already taken", HttpStatus.BAD_REQUEST);
        }
        if (registrationService.emailCheck(userObj.getEmail())) {
            return new ResponseEntity<String>("this email is already taken", HttpStatus.BAD_REQUEST);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>(("value in " + bindingResult.getFieldError().getField() + " is not a valid"), HttpStatus.BAD_REQUEST);
        } else {
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            return new ResponseEntity<String> (registrationService.registration(userObj),HttpStatus.OK);
        }


    }
}
