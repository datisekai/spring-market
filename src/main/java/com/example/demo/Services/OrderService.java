/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Services;

import com.example.demo.Models.Order;
import com.example.demo.Repositories.OrderRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author datly
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(int customerID, int total, String note) {
        Order order = new Order();
        order.setCustomerId(customerID);
        order.setNote(note);
        LocalDate currentDate = LocalDate.now();
        order.setDate(currentDate);
        order.setTotal(total);
        Order lastOrder = orderRepository.findTopByOrderByOrderIdDesc();
        int orderID = lastOrder != null ? lastOrder.getOrderId() + 1 : 0;
        order.setOrderId(orderID);
        System.out.println(orderID);
        return orderRepository.save(order);
    }

}
