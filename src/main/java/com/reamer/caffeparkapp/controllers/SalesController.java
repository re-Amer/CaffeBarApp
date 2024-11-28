package com.reamer.caffeparkapp.controllers;

import com.reamer.caffeparkapp.entities.Sales;
import com.reamer.caffeparkapp.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable int id) {
        Optional<Sales> sales = salesService.getSalesById(id);
        return sales.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
        Sales newSales = salesService.createSales(sales);
        return ResponseEntity.ok(newSales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSales(@PathVariable int id, @RequestBody Sales salesDetails) {
        Sales updatedSales = salesService.updateSales(id, salesDetails);
        if (updatedSales != null) {
            return ResponseEntity.ok(updatedSales);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable int id) {
        if (salesService.getSalesById(id).isPresent()) {
            salesService.deleteSales(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
