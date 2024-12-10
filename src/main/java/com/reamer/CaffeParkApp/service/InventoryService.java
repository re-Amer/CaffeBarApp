package com.reamer.CaffeParkApp.service;

import com.reamer.CaffeParkApp.entities.Expense;
import com.reamer.CaffeParkApp.entities.Inventory;
import com.reamer.CaffeParkApp.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ExpenseService expenseService;
    private final SalesService salesService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ExpenseService expenseService, SalesService salesService) {
        this.inventoryRepository = inventoryRepository;
        this.expenseService = expenseService;
        this.salesService = salesService;
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

        // Get all expenses that are subtractable
        double totalExpenses = expenseService.getExpensesByInventoryId(inventoryId)
                .stream()
                .filter(Expense::isSubtractable)
                .mapToDouble(Expense::getAmount)
                .sum();

        // Get total sales
        double totalSales = calculateTotalSales(inventory);

        // Calculate profit
        return totalSales - totalExpenses + kitchenProfit;
    }

    // Calculate profit from expenses (subtract only subtractable expenses)
    private double calculateProfit(Inventory inventory) {
        double totalProfit = 0;

        for (Expense expense : inventory.getExpenses()) {
            if (expense.isSubtractable()) {
                totalProfit -= expense.getAmount();
            }
        }
        return totalProfit;
    }

    // Calculate total sales from actual sold quantity
    private double calculateTotalSales(Inventory inventory) {
        // Get the actual sold quantity from the sales data
        int soldQuantity = salesService.getSoldQuantityForInventory(inventory.getId()); // Calls SalesService
        double pricePerItem = inventory.getPrice(); // Get price from inventory
        return soldQuantity * pricePerItem;
    }
}
