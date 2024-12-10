package com.reamer.CaffeParkApp.controllers;

import com.reamer.CaffeParkApp.entities.Drink;
import com.reamer.CaffeParkApp.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Drink> getDrinkById(@PathVariable int id) {
        return drinkService.getDrinkById(id);
    }

    @PostMapping
    public Drink createDrink(@RequestBody Drink drink) {
        return drinkService.createDrink(drink);
    }

    @PutMapping("/{id}")
    public Drink updateDrink(@PathVariable int id, @RequestBody Drink drinkDetails) {
        return drinkService.updateDrink(id, drinkDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable int id) {
        drinkService.deleteDrink(id);
    }
}
