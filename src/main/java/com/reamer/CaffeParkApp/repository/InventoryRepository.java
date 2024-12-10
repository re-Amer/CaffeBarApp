package com.reamer.caffeparkapp.repository;

import com.reamer.caffeparkapp.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
