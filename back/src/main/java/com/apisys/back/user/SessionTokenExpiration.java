package com.apisys.back.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionTokenExpiration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private User user;
    private String token;
    private Instant dateExpiration;

    public SessionTokenExpiration(User user, String token, Instant dataExpiration){
        this.dateExpiration = dataExpiration;
        this.user = user;
        this.token = token;
    }
}
