package com.apisys.back.controller;

import com.apisys.back.book.Books;
import com.apisys.back.book.dto.BookDTO;
import com.apisys.back.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String bookMessage(){
        return "Bem vindo a livraria";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Books>> listAll(){
        return bookService.listAllBooks();
    }

    @PostMapping("/new/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createBookRegister(@RequestBody BookDTO bookDTO) throws ParseException {
        return bookService.createBookRegister(bookDTO);
    }
}
