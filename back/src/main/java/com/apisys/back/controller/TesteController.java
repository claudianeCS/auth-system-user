package com.apisys.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucess")
public class TesteController {

    @GetMapping
    public String sucessRote(){
        return "Rota segura acesso liberado!";
    }
}
