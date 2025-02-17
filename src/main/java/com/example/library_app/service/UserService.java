package com.example.library_app.service;

import com.example.library_app.entity.User;
import com.example.library_app.entity.Book;
import com.example.library_app.repository.UserRepository;
import com.example.library_app.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);   // INSERT INTO library_user (...) VALUES (...);
    }

    public void bookBook(Long userId, Long bookId) {
        // SELECT * FROM library_user WHERE id = ???;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // SELECT * FROM books WHERE id = ???;
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already booked");
        }

        // INSERT INTO user_books (user_id, book_id)
        // VALUES (???, ???);
        user.getBooks().add(book);
        book.setAvailable(false);
        bookRepository.save(book);
        userRepository.save(user);
    }

    public void returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        // DELETE FROM user_books
        // WHERE user_id = ? AND book_id = ?;
        user.getBooks().remove(book);
        book.setAvailable(true);
        bookRepository.save(book);
        userRepository.save(user);
    }

    public Set<Book> getUserBooks(Long userId) {
        //        SELECT b.* FROM books b
        //        JOIN user_books ub ON b.id = ub.book_id WHERE ub.user_id = ?;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getBooks();
    }
}

