package com.apisys.back.service;

import com.apisys.back.security.TokenService;
import com.apisys.back.user.Role;
import com.apisys.back.user.User;
import com.apisys.back.user.dto.LoginDTO;
import com.apisys.back.user.dto.LoginResponseDTO;
import com.apisys.back.user.dto.RegisterDTO;
import com.apisys.back.user.repo.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
   private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
    private AuthenticationManager authenticationManager;

    public LoginResponseDTO login(@RequestBody @Valid LoginDTO data) {

        authenticationManager = context.getBean(AuthenticationManager.class);

        var userDetails = loadUserByUsername(data.email());
        if (passwordEncoder.matches(data.password(), userDetails.getPassword())){
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

           // tokenService.veriftyIfTokenExpires(token);

            // get a role of this user
            Role userDetailsRole = userRepository.findByEmail(data.email()).getRole();

            return new LoginResponseDTO(token, userDetailsRole);
        }
        return null;
    }

    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        if (this.userRepository.findByEmail(registerDto.email()) != null ) {
            return ResponseEntity.badRequest().build();
        }

        String encodedPassword = hashPasswordWithBCript(registerDto.password());

        User newUser = new User(registerDto.firstname(), registerDto.email(), encodedPassword);
        newUser.setRole(Role.USER);
        newUser.setCreatedAt(new Date());
        this.userRepository.save(newUser);

        //create a imageURL directory for save a image


        return ResponseEntity.ok().build();
    }

    public String hashPasswordWithBCript(String passowrdHash){
        String bcryptPassword = passwordEncoder.encode(passowrdHash);
        return bcryptPassword;
    }

}
