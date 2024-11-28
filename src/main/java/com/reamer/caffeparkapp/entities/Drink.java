package com.reamer.caffeparkapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drinks")
@Data
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    // Default constructor for JPA
    public Drink() {}
}

