package com.reamer.caffeparkapp.repository;

import com.reamer.caffeparkapp.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
