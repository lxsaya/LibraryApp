package com.example.library_app.repository;

import com.example.library_app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsAvailableTrue();
}

