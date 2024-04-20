//package com.fz.onlineshoestore.controller;
//
//import com.fz.onlineshoestore.model.dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//import org.springframework.http.HttpStatus;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//
//@RestController
//@RequestMapping("/api/login")
//public class LoginController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @PutMapping
//    public ResponseEntity LoginController( @RequestBody UserDto userDto) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                userDto.getUsername(), userDto.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//
//    }
//    @GetMapping
//    public  String ok(){
//        return "ok";
//    }
//}
