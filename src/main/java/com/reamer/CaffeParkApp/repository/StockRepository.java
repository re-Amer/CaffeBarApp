package com.reamer.CaffeParkApp.repository;

import com.reamer.CaffeParkApp.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
