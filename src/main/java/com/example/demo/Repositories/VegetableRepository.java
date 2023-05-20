/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Repositories;

import com.example.demo.Models.Vegetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author datly
 */
@Repository
public interface VegetableRepository extends PagingAndSortingRepository<Vegetable, Integer>{
    @Query(value = "SELECT vegetable.*, SUM(temp.Quantity) as sold " +
       "FROM vegetable, (SELECT VegetableID, Quantity FROM orderdetail) as temp " +
       "WHERE temp.VegetableID = vegetable.VegetableID " +
       "AND vegetable.CatagoryID = :catagoryID " +
       "AND vegetable.Vegetable_Name LIKE CONCAT('%', :Vegetable_Name , '%') "+
       "GROUP BY vegetable.VegetableID " +
       "ORDER BY SUM(temp.Quantity) DESC ", nativeQuery = true)
    Page<Vegetable> findBestSellingByCatagoryID(@Param("catagoryID") int catagoryID,
            @Param("Vegetable_Name") String Vegetable_Name, Pageable pageable);
    @Query(value = "SELECT * " +
        "FROM vegetable WHERE " +
        "vegetable.CatagoryID = :catagoryID " +
        "AND vegetable.Vegetable_Name LIKE CONCAT('%', :Vegetable_Name , '%') "
        , nativeQuery = true)
    Page<Vegetable> findSortPriceProductsByCategory(@Param("catagoryID") int catagoryID, 
            @Param("Vegetable_Name") String Vegetable_Name, Pageable pageable);
    @Query(value = "SELECT * " +
        "FROM vegetable WHERE " +
        "vegetable.Vegetable_Name LIKE CONCAT('%', :Vegetable_Name , '%') "
        , nativeQuery = true)
    Page<Vegetable> findProductsByName( @Param("Vegetable_Name") String Vegetable_Name, Pageable pageable);
    @Query(value = "SELECT * " +
        "FROM vegetable WHERE " +
        "vegetable.CatagoryID = :catagoryID " +
        "AND vegetable.Vegetable_Name LIKE CONCAT('%', :Vegetable_Name , '%') "
        , nativeQuery = true)
    Page<Vegetable> findProductsByCategory(@Param("catagoryID") int catagoryID, 
            @Param("Vegetable_Name") String Vegetable_Name, Pageable pageable);
    @Query(value = "SELECT vegetable.*, SUM(temp.Quantity) as sold " +
       "FROM vegetable, (SELECT VegetableID, Quantity FROM orderdetail) as temp " +
       "WHERE temp.VegetableID = vegetable.VegetableID " +
       "AND vegetable.Vegetable_Name LIKE CONCAT('%', :Vegetable_Name , '%') "+
       "GROUP BY vegetable.VegetableID " +
       "ORDER BY SUM(temp.Quantity) DESC ", nativeQuery = true)
    Page<Vegetable> findProductsByBestSelling( @Param("Vegetable_Name") String Vegetable_Name, Pageable pageable);
}
