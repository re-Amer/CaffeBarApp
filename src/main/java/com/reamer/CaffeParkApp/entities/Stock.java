package com.reamer.CaffeParkApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String shift; // E.g., "Morning", "Evening"

    @Column(nullable = false)
    private String date;

    // Default constructor for JPA
    public Stock() {}
}
