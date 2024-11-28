package com.reamer.caffeparkapp.controllers;

import com.reamer.caffeparkapp.entities.Inventory;
import com.reamer.caffeparkapp.entities.Expense;
import com.reamer.caffeparkapp.service.InventoryService;
import com.reamer.caffeparkapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ExpenseService expenseService;

    @Autowired
    public InventoryController(InventoryService inventoryService, ExpenseService expenseService) {
        this.inventoryService = inventoryService;
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable int id) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.ok(newInventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable int id, @RequestBody Inventory inventoryDetails) {
        Inventory updatedInventory = inventoryService.updateInventory(id, inventoryDetails);
        if (updatedInventory != null) {
            return ResponseEntity.ok(updatedInventory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable int id) {
        if (inventoryService.getInventoryById(id).isPresent()) {
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/calculate-profit")
    public ResponseEntity<Double> calculateProfit(@PathVariable int id, @RequestParam double kitchenProfit) {
        try {
            double totalProfit = inventoryService.calculateTotalProfit(id, kitchenProfit);
            return ResponseEntity.ok(totalProfit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/add-expense")
    public ResponseEntity<Expense> addExpenseToInventory(@PathVariable int id, @RequestBody Expense expense) {
        Optional<Inventory> inventoryOptional = inventoryService.getInventoryById(id);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            expense.setInventory(inventory); // Associate inventory
            Expense newExpense = expenseService.createExpense(expense);
            return ResponseEntity.ok(newExpense);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<Expense>> getExpensesByInventoryId(@PathVariable int id) {
        if (inventoryService.getInventoryById(id).isPresent()) {
            return ResponseEntity.ok(expenseService.getExpensesByInventoryId(id));
        }
        return ResponseEntity.notFound().build();
    }
}
