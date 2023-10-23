package com.apisys.back.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucess")
public class TesteController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // for admin methods
    public String authorizedMessage(){
        return "You are a admin user!!";
    }
}
