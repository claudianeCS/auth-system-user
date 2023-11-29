package com.apisys.back.security;

import com.apisys.back.user.SessionTokenExpiration;
import com.apisys.back.user.User;
import com.apisys.back.user.repo.SessionTokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired // dependece injection
    private SessionTokenRepository sessionTokenRepository;

    protected final String secret = "sysapi";

    public String generateToken(User userModel){
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String tokenGenerated = JWT.create()
                    .withIssuer("auth")
                    .withSubject(userModel.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .withClaim("role", userModel.getRole().toString())
                    .sign(algorithm);

            Optional<SessionTokenExpiration> tokenExisted = this.sessionTokenRepository.findByUser(userModel);
            if (!tokenExisted.isPresent()){
                SessionTokenExpiration tokenRegister = new SessionTokenExpiration(userModel, tokenGenerated, getExpirationDate());
                this.sessionTokenRepository.save(tokenRegister);
                System.out.println("Ta passando por aqui, registro do token existente!!");
            }

            return tokenGenerated;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
        }
    }


    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String tokenValidated = JWT.require(algorithm)
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();

             return tokenValidated;
        }

        catch (JWTVerificationException exception) {
            return "";
        }
    }


    public Boolean veriftyIfTokenExpires(String token){
        // verifica de o token existe no registro, caso tenha atualize a data de Expiração se não remover do registro
        if(token != null){
            Optional<SessionTokenExpiration> tokenSearch = this.sessionTokenRepository.findByToken(token);
            if (tokenSearch.isPresent()){
                // se a dataAtual for maior que a data do token
                Instant dataLocal = Instant.now();
               // System.out.println("Instant" + dataLocal);
            }
        }
        return null;
    }


    private Instant getExpirationDate(){
        Instant dateLocal = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
      //  System.out.println(dateLocal);
        return dateLocal;
    }
}
