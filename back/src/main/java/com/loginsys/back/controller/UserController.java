package com.loginsys.back.controller;

import com.loginsys.back.entity.LoginMessage;
import com.loginsys.back.entity.User;
import com.loginsys.back.entity.dto.LoginDTO;
import com.loginsys.back.entity.dto.UserDTO;
import com.loginsys.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    @PostMapping("/new")
    public String addUser(@RequestBody UserDTO userDTO) {
           String id = userService.addUser(userDTO);
           return id;

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
            LoginMessage loginMessage = userService.loginUser(loginDTO);
            return ResponseEntity.ok().body(loginMessage);
    }

}
