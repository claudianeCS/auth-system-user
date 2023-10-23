package com.apisys.back.service;

import com.apisys.back.book.Books;
import com.apisys.back.book.dto.BookDTO;
import com.apisys.back.book.repo.BooksRepositoty;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {

    // list all books -- public role (USER)
    @Autowired
    private BooksRepositoty booksRepositoty;

    public ResponseEntity<List<Books>> listAllBooks(){
        return ResponseEntity.ok(booksRepositoty.findAll());
    }

    // delete and register new Book protect role (ADMIN)

    public ResponseEntity<Object> createBookRegister(@RequestBody @Valid BookDTO bookDTO){
        Books book = new Books(bookDTO.title(), bookDTO.publishYear(), bookDTO.amount(), bookDTO.synopsis());
        this.booksRepositoty.save(book);
        return ResponseEntity.ok().build();
    }

}
