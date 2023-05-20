/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Services;

import com.example.demo.Models.Order;
import com.example.demo.Models.OrderDetail;
import com.example.demo.Models.Vegetable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repositories.OrderDetailRepository;
import java.util.Optional;

/**
 *
 * @author haidu
 */
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository ordrerDetailRepository;

    public Iterable<OrderDetail> getOrderDetailByOrderID(int orderID) {
        Iterable<OrderDetail> entities = ordrerDetailRepository.findProductByOrderID(orderID);
        return ordrerDetailRepository.findProductByOrderID(orderID);
    }
}
