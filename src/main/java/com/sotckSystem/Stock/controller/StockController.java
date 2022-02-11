package com.sotckSystem.Stock.controller;

import com.sotckSystem.Stock.model.DTO.StockDTO;

import com.sotckSystem.Stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StockController {

    @Autowired
    private StockService service;

    @GetMapping(value = "/")
    public String home(Model model){
        StockDTO stockBusca = new StockDTO();
        model.addAttribute("stockBusca", stockBusca);
        model.addAttribute("stocks", service.findAll());
        return "index";
    }
    @GetMapping(value = "/filterToday")
    public String filterToday(Model model){
        List<StockDTO> dtos = service.findByToday();
        StockDTO stockBusca = new StockDTO();
        model.addAttribute("stockBusca",stockBusca);
        model.addAttribute("stocks", dtos);
        return "index";
    }
    @GetMapping(value = "/showAddPage")
    public String showAddPage(Model model){
        StockDTO dto = new StockDTO();
        model.addAttribute("stockdto", dto);
        return "pageAdd";
    }
    @GetMapping(value = "/showFormForUpdate/{id}")
    public String updateStock(@PathVariable("id") Long id, Model model){
        StockDTO dto = service.findById(id);
        model.addAttribute("stockForUp", dto);
        return "pageUp";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@ModelAttribute("id") Long id, Model model){
        service.delete(id);
        return "redirect:/";
    }
    @PostMapping(value = "/filterName")
    public String filterName(@ModelAttribute("stockBusca") StockDTO stockBusca, Model model){
        StockDTO busca = new StockDTO();
        model.addAttribute("stockBusca",busca);
        List<StockDTO> dtos = service.findByName(stockBusca.getName());
        model.addAttribute("stocks", dtos);
        return "index";
    }
    @PostMapping(value = "/saveStock")
    public String saveStock(@ModelAttribute("stockdto") StockDTO dto, Model model){
        service.save(dto);
        return "redirect:/";
    }
    @PostMapping(value = "/updateStock")
    public String updateStock(@ModelAttribute("stockForUp") StockDTO stockUp, Model model){
        service.update(stockUp);
        return "redirect:/";
    }
}
