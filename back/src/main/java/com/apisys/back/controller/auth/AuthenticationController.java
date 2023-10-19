package com.apisys.back.controller.auth;

import com.apisys.back.service.AuthenticationService;
import com.apisys.back.user.User;
import com.apisys.back.user.dto.LoginDTO;
import com.apisys.back.user.dto.RegistroDTO;
import com.apisys.back.user.repo.UserRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth/acess")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        return authenticationService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegistroDTO registroDTO){
        return authenticationService.register(registroDTO);
    }

}
