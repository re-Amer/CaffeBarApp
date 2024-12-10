package com.reamer.CaffeParkApp.repository;

import com.reamer.CaffeParkApp.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
