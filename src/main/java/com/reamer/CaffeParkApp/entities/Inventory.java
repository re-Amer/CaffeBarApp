package com.reamer.CaffeParkApp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String date; // Date of inventory check

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    @Column(nullable = false)
    private double profit = 0;  // Add a profit field for the shift

    // Default constructor for JPA
    public Inventory() {}

    // Add getter and setter for price from associated drink (if not using Lombok for all getters)
    public double getPrice() {
        if (this.drink != null) {
            return this.drink.getPrice();
        }
        return 0; // Return 0 if no associated drink is found
    }

    // Other getters and setters will be handled by Lombok (@Data) automatically
}
