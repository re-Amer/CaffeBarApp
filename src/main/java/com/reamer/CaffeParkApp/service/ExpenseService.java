package com.reamer.caffeparkapp.service;

import com.reamer.caffeparkapp.entities.Expense;
import com.reamer.caffeparkapp.entities.Inventory;
import com.reamer.caffeparkapp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense, Inventory inventory) {
        expense.setInventory(inventory); // Link the expense to the inventory
        return expenseRepository.save(expense); // Save and return the expense
    }

    public List<Expense> getExpensesByInventoryId(int inventoryId) {
        return expenseRepository.findByInventoryId(inventoryId); // Fetch expenses linked to an inventory
    }

    public boolean deleteExpense(int id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true; // Successful deletion
        }
        return false; // Expense not found
    }
}
