package com.example.libraryapp.model.controller;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.model.User;
import com.example.libraryapp.model.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LibraryController {
    private final LibraryService svc;
    public LibraryController(LibraryService svc){ this.svc = svc; }

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> body){
        String name = (String) body.get("name");
        String phone = (String) body.get("phone");
        boolean premium = body.get("premium") != null && (Boolean) body.get("premium");
        try {
            User u = svc.registerUser(name, phone, premium);
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        String phone = body.get("phone");
        return svc.login(phone)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping("/books")
    public List<Book> listBooks(){ return svc.listBooks(); }
    
    @GetMapping("/users")
    public List<User> listUsers() {
        return svc.listUsers();
    }


    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Map<String,Object> body){
        try {
            String name = (String) body.get("name");
            BigDecimal price = new BigDecimal(String.valueOf(body.get("price")));
            Book b = svc.addBook(name, price);
            return ResponseEntity.ok(b);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/books/{id}/buy")
    public ResponseEntity<?> buy(@PathVariable Long id, @RequestBody Map<String,String> body){
        String phone = body.get("phone");
        try {
            String res = svc.buyBook(id, phone);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/books/{id}/read")
    public ResponseEntity<?> read(@PathVariable Long id, @RequestBody Map<String,String> body){
        String phone = body.get("phone");
        try {
            String res = svc.readInLibrary(id, phone);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
