package com.reamer.CaffeParkApp.repository;

import com.reamer.CaffeParkApp.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByInventoryId(int inventoryId);
}
