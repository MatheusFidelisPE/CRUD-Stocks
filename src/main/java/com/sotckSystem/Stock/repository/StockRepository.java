package com.sotckSystem.Stock.repository;

import com.sotckSystem.Stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<List<Stock>>findByDate(LocalDate today);

    Optional<List<Stock>> findByName(String name);
}
