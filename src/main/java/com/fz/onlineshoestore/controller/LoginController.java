//package com.fz.onlineshoestore.controller;
//
//import com.fz.onlineshoestore.model.dto.UserDto;
//import com.fz.onlineshoestore.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping({"/signin", "/signin/"})
//public class LoginController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Autowired
//    UserService userService;
//
//    @PostMapping
//    public ResponseEntity<String> authenticateUser(@RequestParam String username, @RequestParam String password) {
//        System.out.println("username: " + username + " password: " + password);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//    }
//
//    @GetMapping
//    public String ok() {
//        return "ok";
//    }
//}
