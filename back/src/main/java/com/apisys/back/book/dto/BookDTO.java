package com.apisys.back.book.dto;

import java.util.Date;

public record BookDTO(String title, Date publishYear, Integer amount, String synopsis) {
}
