package com.apisys.back.controller;

import com.apisys.back.service.AuthenticationService;
import com.apisys.back.user.dto.LoginDTO;
import com.apisys.back.user.dto.LoginResponseDTO;
import com.apisys.back.user.dto.RegisterDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String messagePublicTeste(){
        return "Menssagem se OK!";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response) {


        // the service have to recive all validations of email and passoword and
        // return a jwt token genereted method
        LoginResponseDTO tokenValue = authenticationService.login(loginDTO);

        if (tokenValue != null){
            // Crie um novo cookie
            Cookie cookie = new Cookie("sysapi", tokenValue.token());

            // Defina a duração do cookie (em segundos)
            cookie.setMaxAge(3600); // 1 hora

            // Adicione o cookie à resposta
            response.addCookie(cookie);

            return ResponseEntity.ok(tokenValue);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        return authenticationService.register(registerDto);
    }



/*    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {

    }*/
}
