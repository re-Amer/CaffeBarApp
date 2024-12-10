package com.reamer.caffeparkapp.service;

import com.reamer.caffeparkapp.entities.Stock;
import com.reamer.caffeparkapp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(int id) {
        return stockRepository.findById(id);
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(int id, Stock stockDetails) {
        if (stockRepository.existsById(id)) {
            stockDetails.setId(id); // Ensure the ID is set for update
            return stockRepository.save(stockDetails);
        }
        return null;
    }

    public void deleteStock(int id) {
        stockRepository.deleteById(id);
    }
}
