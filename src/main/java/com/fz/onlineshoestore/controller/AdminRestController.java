package com.fz.onlineshoestore.controller;

import com.fz.onlineshoestore.model.Shoe;
import com.fz.onlineshoestore.service.AdminServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/admin")
@Slf4j
public class AdminRestController {
    AdminServices adminServices;

    public AdminRestController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }


    //----------------------------------------------------------------add
    @PutMapping(path = "/add/shoe")
    public ResponseEntity addShoe(@RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam Long price,
                                  @RequestParam MultipartFile photoFile) throws IOException {

        if (name == null || description == null || price == null || photoFile == null) {

            return new ResponseEntity<>("Some parameters are missing", HttpStatus.BAD_REQUEST);
        }
        Shoe shoe = Shoe.builder().name(name).description(description).price(price).photoFile(photoFile).build();
        try {
            return new ResponseEntity<>(adminServices.addShoe(shoe), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------------------------------------REMOVE
    @GetMapping(path = "/remove/{id}")
    public ResponseEntity<String> removeShoe(@PathVariable Long id) {
        try {
            return new ResponseEntity<String>(adminServices.removeShoe(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------------------------------------GET all or one
    @GetMapping({"/find/{id}", "/find/","/find"})
    public Optional findShoe(@PathVariable(required = false) Long id) {
        if (id == null) {
            return new ResponseEntity<Optional<List<Shoe>>>(adminServices.getShoes(), HttpStatus.OK).getBody();
        } else {
            return new ResponseEntity<Optional<Shoe>>(adminServices.getShoe(id), HttpStatus.OK).getBody();
        }
    }
    //----------------------------------------------------------------

}
