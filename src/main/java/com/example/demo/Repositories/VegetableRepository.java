/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Repositories;

import com.example.demo.Models.Vegetable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author datly
 */
@Repository
public interface VegetableRepository extends PagingAndSortingRepository<Vegetable, Integer>{
    @Query("SELECT p FROM Vegetable as p, OrderDetail as od WHERE p.CatagoryID = :catagoryID AND p.VegetableID = od.VegetableID GROUP BY p.VegetableID ORDER BY SUM(od.Quantity) DESC")
    Page<Vegetable> findByCatagoryID(@Param("catagoryID") int catagoryID, Pageable pageable);
}
