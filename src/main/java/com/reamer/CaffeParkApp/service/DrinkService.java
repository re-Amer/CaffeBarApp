package com.reamer.CaffeParkApp.service;

import com.reamer.CaffeParkApp.entities.Drink;
import com.reamer.CaffeParkApp.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Optional<Drink> getDrinkById(int id) {
        return drinkRepository.findById(id);
    }

    public Drink createDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    public Drink updateDrink(int id, Drink drinkDetails) {
        if (drinkRepository.existsById(id)) {
            drinkDetails.setId(id); // Ensure the ID is set for update
            return drinkRepository.save(drinkDetails);
        }
        return null;
    }

    public void deleteDrink(int id) {
        drinkRepository.deleteById(id);
    }
}
