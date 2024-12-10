package com.reamer.CaffeParkApp.repository;

import com.reamer.CaffeParkApp.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
}
