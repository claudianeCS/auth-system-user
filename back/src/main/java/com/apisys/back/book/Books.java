package com.apisys.back.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer publishYear;

    private Integer amount; // qtd

    public Books(String title, Integer publishYear, Integer amount, String synopsis) {
        this.title = title;
        this.publishYear = publishYear;
        this.amount = amount;
        this.synopsis = synopsis;
    }

    @Column(length = 350)
    private String synopsis;
}
