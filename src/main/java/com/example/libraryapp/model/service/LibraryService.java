package com.example.libraryapp.model.service;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.repository.BookRepository;
import com.example.libraryapp.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;

    public LibraryService(UserRepository userRepo, BookRepository bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    // Register user
    public User registerUser(String name, String phone, boolean premium) {
        // check if phone already registered
        if (userRepo.findByPhone(phone).isPresent()) {
            throw new IllegalArgumentException("Phone already registered");
        }
        User u = new User(name, phone, premium);
        return userRepo.save(u);
    }

    // Login by phone (returns user if exists)
    public Optional<User> login(String phone) {
        return userRepo.findByPhone(phone);
    }

    // ✅ Add a book
    public Book addBook(String name, BigDecimal price) {
        Book b = new Book(name, price);
        return bookRepo.save(b);
    }

    // List all books
    public List<Book> listBooks() {
        return bookRepo.findAll();
    }

    // Buy book (only for premium users)
    @Transactional
    public String buyBook(Long bookId, String userPhone) {
        User user = userRepo.findByPhone(userPhone)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.isPremium()) {
            return "Only premium users can buy books.";
        }

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (book.isSold()) {
            return "Book already sold.";
        }

        book.setSold(true);
        bookRepo.save(book);
        return "Book purchased successfully: " + book.getName();
    }

    // Read book in library (allowed for all users)
    public String readInLibrary(Long bookId, String userPhone) {
        userRepo.findByPhone(userPhone)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        return "You can read: " + book.getName();
    }

    public List<User> listUsers() {
        return userRepo.findAll();
    }

}
