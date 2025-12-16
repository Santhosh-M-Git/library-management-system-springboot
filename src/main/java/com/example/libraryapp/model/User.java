package com.example.libraryapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    private String phone;

    // Oracle doesn't support boolean → use int
    private int premium; // 1 = premium, 0 = normal

    public User() {}

    public User(String name, String phone, boolean premium) {
        this.name = name;
        this.phone = phone;
        this.premium = premium ? 1 : 0;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public boolean isPremium() { return premium == 1; }

    public void setPremium(boolean premium) { this.premium = premium ? 1 : 0; }
}
