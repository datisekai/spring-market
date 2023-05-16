/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Services;

import com.example.demo.Models.Vegetable;
import com.example.demo.Repositories.VegetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author datly
 */
@Service
public class VegetableService {
    @Autowired
    private VegetableRepository vegetableRepository;
    
    public Page<Vegetable> getBestSellingProductsByCategory(int catagoryID, Pageable pageable) {
        return vegetableRepository.findByCatagoryID(catagoryID, pageable);
    }
    
    public Page<Vegetable> getAll(Pageable pageable){
        System.out.println(pageable);
        return vegetableRepository.findAll(pageable);
    }
}
