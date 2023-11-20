package com.apisys.back.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @Temporal(TemporalType.DATE)
    private Date publishYear;

    private Integer amount; // qtd

    private String urlImage;

    public Books(String title, Date publishYear, Integer amount, String synopsis) {
        this.title = title;
        this.publishYear = publishYear;
        this.amount = amount;
        this.synopsis = synopsis;
    }

    @Column(length = 350)
    private String synopsis;
}
