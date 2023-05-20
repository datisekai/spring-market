/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.Models.Order;
import com.example.demo.Models.OrderDetail;
import com.example.demo.Models.Vegetable;
import com.example.demo.Services.OrderDetailService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author haidu
 */
@Controller
public class OrderDetailController {
    
    @Autowired
    private OrderDetailService orderDetailService;
    
    @RequestMapping(value = "/orderdetail", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String idOderStr = request.getParameter("id");
        int idOder = -1;
        if (idOderStr != null) {
            try {
                idOder = Integer.parseInt(idOderStr);
            } catch (NumberFormatException e) {
            }
        }
        Iterable<OrderDetail> OrderDetails = null;
        if(idOder!=-1){
            OrderDetails = orderDetailService.getOrderDetailByOrderID(idOder);
        }
        model.addAttribute("orderDetails", OrderDetails);
        return "pages/orderdetail";
    }
}
