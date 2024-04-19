package com.fz.onlineshoestore.controller;

import com.fz.onlineshoestore.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //add shoe to buy
    @PostMapping("/add")
    public String addUser(@RequestParam Long shoeId) throws Exception {
        //get the user
        String currentUser = ((UserDetails) userService.getUser()).getUsername();
        //add the shoe
        return userService.addShoe(shoeId, currentUser);


    }
    //test
    @GetMapping
    public String getName() {
        return "success";
    }
    //removes shoe from list
    @PostMapping("/remove")
    public String removeShoe(@RequestParam Long shoeId) throws Exception {
        String currentUser = ((UserDetails) userService.getUser()).getUsername();
        return userService.removeShoe(shoeId, currentUser);
    }
    //show all users shoes
    @GetMapping ("/show")
    public Object[] getShoes() throws Exception {
        String currentUser = ((UserDetails) userService.getUser()).getUsername();
        return userService.getShoes(currentUser).toArray();
    }
}
