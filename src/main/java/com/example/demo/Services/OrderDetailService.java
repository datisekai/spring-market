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
        for (OrderDetail o : entities) {
//            System.out.println("OrderDetail ID: " + o.getId());
            System.out.println("Quantity: " + o.getQuantity());
            System.out.println("Price: " + o.getPrice());

            // Lấy thông tin chi tiết sản phẩm
//            Vegetable vegetable = o.getVegetable();
//            System.out.println("Vegetable ID: " + vegetable.getVegetableName());
//            System.out.println("Vegetable Name: " + vegetable.getImage());
            // ...
        }
//        entities.ifPresent(orderDetail -> {
//            // Xử lý khi OrderDetail tồn tại
//            // Ví dụ:
//            System.out.println("OrderDetail ID: " + orderDetail.getId());
//            System.out.println("Quantity: " + orderDetail.getQuantity());
//            System.out.println("Price: " + orderDetail.getPrice());
//        });
        return ordrerDetailRepository.findProductByOrderID(orderID);
    }
}
