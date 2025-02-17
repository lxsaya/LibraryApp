package com.example.library_app.controller;

import com.example.library_app.entity.User;
import com.example.library_app.entity.Book;
import com.example.library_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody String name) {
        User user = userService.createUser(name);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{userId}/books/{bookId}/book")
    public ResponseEntity<Void> bookBook(@PathVariable Long userId, @PathVariable Long bookId) {
        userService.bookBook(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}/books/{bookId}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long userId, @PathVariable Long bookId) {
        userService.returnBook(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<Set<Book>> getUserBooks(@PathVariable Long userId) {
        Set<Book> userBooks = userService.getUserBooks(userId);
        return ResponseEntity.ok(userBooks);
    }
}


