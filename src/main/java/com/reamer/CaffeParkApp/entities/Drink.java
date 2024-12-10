package com.reamer.CaffeParkApp.entities;

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

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false) // Ensure this maps correctly
    private Inventory inventory;

    // Default constructor for JPA
    public Drink() {}
}

