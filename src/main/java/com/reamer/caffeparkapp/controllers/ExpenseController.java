package com.reamer.caffeparkapp.controllers;

import com.reamer.caffeparkapp.entities.Expense;
import com.reamer.caffeparkapp.entities.Inventory;
import com.reamer.caffeparkapp.service.ExpenseService;
import com.reamer.caffeparkapp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // <-- Ensure this import is here
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final InventoryService inventoryService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, InventoryService inventoryService) {
        this.expenseService = expenseService;
        this.inventoryService = inventoryService;
    }

    // Add expense to a specific inventory
    @PostMapping("/{id}/add-expense")
    public ResponseEntity<Expense> addExpenseToInventory(@PathVariable int id, @RequestBody Expense expense) {
        Optional<Inventory> inventoryOptional = inventoryService.getInventoryById(id);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            Expense newExpense = expenseService.createExpense(expense, inventory); // Pass inventory to service
            return ResponseEntity.ok(newExpense); // Return the created expense
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if inventory not found
        }
    }

    // Get all expenses for a specific inventory
    @GetMapping("/inventory/{inventoryId}")
    public List<Expense> getExpensesByInventory(@PathVariable int inventoryId) {
        return expenseService.getExpensesByInventoryId(inventoryId);
    }

    // Delete an expense by ID
    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
    }

}
