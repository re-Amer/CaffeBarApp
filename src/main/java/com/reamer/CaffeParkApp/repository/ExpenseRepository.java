package com.reamer.caffeparkapp.repository;

import com.reamer.caffeparkapp.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByInventoryId(int inventoryId);
}
