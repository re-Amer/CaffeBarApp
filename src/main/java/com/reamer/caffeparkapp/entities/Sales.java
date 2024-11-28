package com.reamer.caffeparkapp.entities;


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
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(nullable = false)
    private int quantitySold;

    @Column(nullable = false)
    private double totalPrice; // Total price for the sold quantity

    @Column(nullable = false)
    private String date; // Date of sale

    // Default constructor for JPA
    public Sales() {}
}
