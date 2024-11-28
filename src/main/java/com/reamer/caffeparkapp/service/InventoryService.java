package com.reamer.caffeparkapp.service;

import com.reamer.caffeparkapp.entities.Expense;
import com.reamer.caffeparkapp.entities.Inventory;
import com.reamer.caffeparkapp.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ExpenseService expenseService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ExpenseService expenseService) {
        this.inventoryRepository = inventoryRepository;
        this.expenseService = expenseService;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(int id) {
        return inventoryRepository.findById(id);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(int id, Inventory inventoryDetails) {
        return inventoryRepository.findById(id).map(existingInventory -> {
            inventoryDetails.setId(id); // Ensure the correct ID
            double totalProfit = calculateProfit(inventoryDetails);
            inventoryDetails.setProfit(totalProfit); // Update calculated profit
            return inventoryRepository.save(inventoryDetails);
        }).orElse(null); // If inventory is not found, return null
    }

    public void deleteInventory(int id) {
        inventoryRepository.deleteById(id);
    }

    public double calculateTotalProfit(int inventoryId, double kitchenProfit) {
        Inventory inventory = getInventoryById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for ID: " + inventoryId));

        double totalExpenses = expenseService.getExpensesByInventoryId(inventoryId)
                .stream()
                .filter(Expense::isSubtractable)
                .mapToDouble(Expense::getAmount)
                .sum();

        double totalSales = calculateTotalSales(inventory);

        return totalSales - totalExpenses + kitchenProfit;
    }

    private double calculateProfit(Inventory inventory) {
        double totalProfit = 0;

        for (Expense expense : inventory.getExpenses()) {
            if (expense.isSubtractable()) {
                totalProfit -= expense.getAmount();
            }
        }
        return totalProfit;
    }

    private double calculateTotalSales(Inventory inventory) {
        int soldQuantity = 10; // Placeholder: replace with real logic
        double pricePerDrink = inventory.getDrink().getPrice();
        return soldQuantity * pricePerDrink;
    }
}
