package com.reamer.CaffeParkApp.repository;

import com.reamer.CaffeParkApp.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT SUM(s.quantitySold) FROM Sales s WHERE s.drink.inventory.id = :inventoryId")
    int countSoldItemsByInventoryId(@Param("inventoryId") int inventoryId);

    @Query("SELECT s FROM Sales s WHERE s.drink.inventory.id = :inventoryId")
    List<Sales> findSalesByInventoryId(@Param("inventoryId") int inventoryId);
}
