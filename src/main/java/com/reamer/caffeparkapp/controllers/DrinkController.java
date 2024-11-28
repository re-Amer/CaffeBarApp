package com.reamer.caffeparkapp.controllers;

import com.reamer.caffeparkapp.entities.Drink;
import com.reamer.caffeparkapp.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable int id) {
        Optional<Drink> drink = drinkService.getDrinkById(id);
        return drink.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
        Drink newDrink = drinkService.createDrink(drink);
        return ResponseEntity.ok(newDrink);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable int id, @RequestBody Drink drinkDetails) {
        Drink updatedDrink = drinkService.updateDrink(id, drinkDetails);
        if (updatedDrink != null) {
            return ResponseEntity.ok(updatedDrink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable int id) {
        if (drinkService.getDrinkById(id).isPresent()) {
            drinkService.deleteDrink(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
