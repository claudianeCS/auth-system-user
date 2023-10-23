package com.apisys.back.book.repo;

import com.apisys.back.book.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BooksRepositoty extends JpaRepository<Books, UUID> {
}
