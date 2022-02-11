package com.sotckSystem.Stock.service;

import com.sotckSystem.Stock.mapper.StockMapper;
import com.sotckSystem.Stock.model.DTO.StockDTO;
import com.sotckSystem.Stock.model.Stock;
import com.sotckSystem.Stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return mapper.toDtoAll(repository.findAll()) ;
    }

    @Transactional()
    public void save(StockDTO dto) {
        repository.save(mapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository
                .findByDate(LocalDate.now())
                .map(mapper::toDtoAll)
                .orElseThrow(RuntimeException::new);
    }
    @Transactional(readOnly = true)
    public List<StockDTO> findByName(String name) {
        return repository
                .findByName(name)
                .map(mapper::toDtoAll)
                .orElseThrow(RuntimeException::new);
    }
    @Transactional()
    public StockDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).get());
    }
    @Transactional()
    public void update(StockDTO stockUp) {
        Stock stock = mapper.toEntity(stockUp);
        stock.setId(stockUp.getId());
        repository.save(stock);
    }
    @Transactional()
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
