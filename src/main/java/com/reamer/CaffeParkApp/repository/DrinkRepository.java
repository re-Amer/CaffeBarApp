package com.reamer.caffeparkapp.repository;

import com.reamer.caffeparkapp.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
}
