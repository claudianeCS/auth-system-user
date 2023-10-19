package com.apisys.back.service;

import com.apisys.back.security.JwtService;
import com.apisys.back.user.User;
import com.apisys.back.user.dto.LoginDTO;
import com.apisys.back.user.dto.RegistroDTO;
import com.apisys.back.user.dto.TokenDTO;
import com.apisys.back.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private AuthenticationManager authenticationManager;
    public ResponseEntity<Object> login(LoginDTO loginDTO) {
        authenticationManager = context.getBean(AuthenticationManager.class);
// verifica as credenciais de login e rotorna a geração do token
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword); // vai mudar o status para authenticate = true
        var token = jwtService.generateToken((User) auth.getPrincipal()); // vai gerar o token com o username
        return ResponseEntity.ok(new TokenDTO(token)); // retorna o token
    }


    public ResponseEntity<Object> register(@RequestBody RegistroDTO registroDTO){

        User userEmail = userRepository.findByEmail(registroDTO.getUsername());
        if (userEmail !=null ) {
            return ResponseEntity.badRequest().build(); // se houver um email existente ele vai retornar um Bad Resquest
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registroDTO.getPassword()); // encriptografa a senha

        User newUser = new User(registroDTO.getUsername(), encryptedPassword, registroDTO.getRole());
        newUser.setCreatedAt(new Date(System.currentTimeMillis())); //seta a data de criação
        this.userRepository.save(newUser); // salva
        return ResponseEntity.ok().build(); // retorna o status de usuario logado !!!
    }
}
