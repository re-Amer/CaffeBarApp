package com.reamer.caffeparkapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense")
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Column(nullable = false)
    private String description;  // Describes the type of expense (e.g., "kitchen", "broken bottle")

    @Column(nullable = false)
    private double amount;  // The amount of the expense (e.g., 10, 2)

    @Column(nullable = false)
    private boolean isSubtractable = true;  // True if the expense should be subtracted from the profit

    // Default constructor for JPA
    public Expense() {}
}
