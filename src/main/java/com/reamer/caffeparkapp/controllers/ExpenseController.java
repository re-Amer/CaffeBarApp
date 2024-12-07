package com.reamer.caffeparkapp.controllers;

import com.reamer.caffeparkapp.entities.Expense;
import com.reamer.caffeparkapp.entities.Inventory;
import com.reamer.caffeparkapp.service.ExpenseService;
import com.reamer.caffeparkapp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
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

    @PostMapping("/{inventoryId}/add")
    public ResponseEntity<Expense> addExpenseToInventory(@PathVariable int inventoryId, @RequestBody Expense expense) {
        Optional<Inventory> inventoryOptional = inventoryService.getInventoryById(inventoryId);
        if (inventoryOptional.isPresent()) {
            Expense newExpense = expenseService.createExpense(expense, inventoryOptional.get());
            return ResponseEntity.ok(newExpense); // Return the created expense
        }
        return ResponseEntity.notFound().build(); // Inventory not found
    }

    @GetMapping("/inventory/{inventoryId}")
    public ResponseEntity<List<Expense>> getExpensesByInventory(@PathVariable int inventoryId) {
        List<Expense> expenses = expenseService.getExpensesByInventoryId(inventoryId);
        if (expenses.isEmpty()) {
            return ResponseEntity.noContent().build(); // No expenses found for inventory
        }
        return ResponseEntity.ok(expenses); // Return expenses
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int expenseId) {
        if (expenseService.deleteExpense(expenseId)) {
            return ResponseEntity.noContent().build(); // Deleted successfully
        }
        return ResponseEntity.notFound().build(); // Expense not found
    }
}
