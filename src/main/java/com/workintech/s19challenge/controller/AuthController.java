package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.RegisterUser;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.user.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

private AuthenticationService authenticationService;

@Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @GetMapping("/")
    public String sayhi(){
    return "aksdasd";
    }


    @PostMapping("/register")
    public User register(@RequestBody RegisterUser registerUser){

        User newUser = authenticationService.register(registerUser.name(), registerUser.email(), registerUser.password());
        return newUser;
    }
}
