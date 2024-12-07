package com.reamer.caffeparkapp.service;

import com.reamer.caffeparkapp.entities.Sales;
import com.reamer.caffeparkapp.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Optional<Sales> getSalesById(int id) {
        return salesRepository.findById(id);
    }

    public Sales createSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public Sales updateSales(int id, Sales salesDetails) {
        if (salesRepository.existsById(id)) {
            salesDetails.setId(id); // Ensure the ID is set for update
            return salesRepository.save(salesDetails);
        }
        return null;
    }

    public void deleteSales(int id) {
        salesRepository.deleteById(id);
    }

    // Method to get sold quantity for a specific inventory item by its ID
    public int getSoldQuantityForInventory(int inventoryId) {
        // Assuming there's a method in the SalesRepository that counts sales for an inventory item
        return salesRepository.countSoldItemsByInventoryId(inventoryId);  // Adjust if necessary
    }
}
