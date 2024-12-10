package com.reamer.CaffeParkApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sales")
@Data
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)  // Ensure the column name matches your DB schema
    private Drink drink;

    @Column(nullable = false)
    private int quantitySold;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String date;

    // Default constructor for JPA
    public Sales() {}
}
