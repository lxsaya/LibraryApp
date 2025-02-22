package com.example.library_app.service;

import com.example.library_app.entity.Book;
import com.example.library_app.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        return bookRepository.save(book);   // INSERT INTO books (...) VALUES (...);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsAvailableTrue();    // SELECT * FROM books WHERE is_available = true;
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);    // DELETE FROM books WHERE id = ???(какое то id книги);
    }
}

