package com.fz.onlineshoestore.controller;


import com.fz.onlineshoestore.model.Shoe;
import com.fz.onlineshoestore.service.AdminServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class PublicController
{
    AdminServices adminServices;
    public PublicController (AdminServices adminServices){
        this.adminServices = adminServices;
    }
    @GetMapping({"/find/{id}", "/find/","/find"})
    public Optional findShoe(@PathVariable(required = false) Long id) {
        if (id == null) {
            return new ResponseEntity<Optional<List<Shoe>>>(adminServices.getShoes(), HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<Optional<Shoe>>(adminServices.getShoe(id), HttpStatus.OK).getBody();
        }
    }
}
