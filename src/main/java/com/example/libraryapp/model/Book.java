package com.example.libraryapp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private int sold; // 0 = not sold, 1 = sold

    public Book() {}

    public Book(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.sold = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getSold() { return sold; }
    public void setSold(int sold) { this.sold = sold; }

    public boolean isSold() { return sold == 1; }
    public void setSold(boolean b) { this.sold = b ? 1 : 0; }
}
