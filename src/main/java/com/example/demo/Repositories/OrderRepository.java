/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Repositories;

import com.example.demo.Models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author datly
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findTopByOrderByOrderIdDesc();
    @Query(value="SELECT * FROM `order` as ord WHERE ord.CustomerID= :customerID",nativeQuery = true)
    Page<Order> findOrderByCustomerID(@Param("customerID") String CustomerID, Pageable pageable);
    
}
