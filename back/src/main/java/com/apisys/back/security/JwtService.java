package com.apisys.back.security;

import com.apisys.back.user.User;
import com.apisys.back.user.repo.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtService {

    private String secret = "apisys"; // create a project name secret

   @Autowired
    private UserRepository userRepository;


    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // gera um algoritimo a partir de uma variavel

            String token = JWT.create()
                    .withIssuer("auth")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;


        } catch (JWTCreationException exception) {
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
        }

        catch (JWTVerificationException exception) {
            return "";
        }
    }

    // created a new token if the oldest is expired
    public String generatedRefreshToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth").build();

            DecodedJWT decodedJWT = verifier.verify(token);
            //decodifica o token com o decoded
            if (decodedJWT.getExpiresAt().before(new Date())){
                // o token esta expirado
                String newSubject = decodedJWT.getSubject();

                User userEmail = userRepository.findByEmail(newSubject); // gambiarra
                return generateToken(userEmail);
            }
            return token;

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    // getExpirionDate()
    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
