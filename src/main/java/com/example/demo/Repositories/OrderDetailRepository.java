/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.Repositories;

import com.example.demo.Models.OrderDetail;
import com.example.demo.Models.Vegetable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author haiduA
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    @Query(value = "SELECT vegetable.* , temp.Quantity " +
        ", temp.id , temp.OrderID FROM vegetable " +
        " ,orderdetail as temp " +
        " WHERE temp.VegetableID = vegetable.VegetableID and  temp.OrderID = :orderID",nativeQuery = true)
    Iterable<OrderDetail> findProductByOrderID(@Param("orderID") int orderID);
}
