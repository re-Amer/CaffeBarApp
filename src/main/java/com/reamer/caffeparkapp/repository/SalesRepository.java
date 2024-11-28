package com.reamer.caffeparkapp.repository;

import com.reamer.caffeparkapp.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
