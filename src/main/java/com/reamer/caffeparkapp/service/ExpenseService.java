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
        return expenseRepository.save(expense); // Save the expense
    }

    public List<Expense> getExpensesByInventoryId(int inventoryId) {
        return expenseRepository.findByInventoryId(inventoryId); // Assuming a method like this exists
    }
    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
    }

}
