package com.example.libraryapp.model.repository;

import com.example.libraryapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    long count();
}
