/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Repositories;

import com.example.demo.Models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author datly
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findTopByOrderByOrderIdDesc();
}
