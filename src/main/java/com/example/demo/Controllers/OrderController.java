/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.Models.Order;
import com.example.demo.Models.Vegetable;
import com.example.demo.Repositories.OrderRepository;
import com.example.demo.Services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author datly
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String index(HttpSession session, Model model, HttpServletRequest request) {
        if (session.getAttribute("fullName") == null) {
            return "redirect:/login";
        }
        String customerID = null;
        Object sessionAttribute = session.getAttribute("customerID");
        if (sessionAttribute != null) {
            customerID = sessionAttribute.toString();
        }
        if (customerID != null) {
            int limit = 10, page = 1;

            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                }
            }
            Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Order> orders = orderService.getOrderByCustomerID(customerID, pageable);
        System.out.println(orders.getContent()+"check");
        for(Order o : orders.getContent()){
            System.out.println(o.getTotal());
        }
        model.addAttribute("orders", orders.getContent());
        model.addAttribute("totalPage", orders.getTotalPages());
        model.addAttribute("totalElement", orders.getTotalElements());
        model.addAttribute("currentPage",page);
        }

        return "pages/order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String createOrder(HttpSession session, @RequestParam(value = "note", defaultValue = "") String note) {
        String customerID = null;
        Object sessionAttribute = session.getAttribute("customerID");
        if (sessionAttribute != null) {
            customerID = sessionAttribute.toString();
        }
        if (customerID != null) {
            ArrayList<Vegetable> products = new ArrayList<>();
            ArrayList<Vegetable> tempSession = (ArrayList<Vegetable>) session.getAttribute("carts");
            if (tempSession != null) {
                products = tempSession;
            }
            int sum = 0;
            for (Vegetable v : products) {
                sum += v.getPrice() * v.getAmount();
            }
            Order order = orderService.createOrder(Integer.parseInt(customerID), sum, note);

            if (order != null) {
                return "redirect:/order";
            }

        }

        return "";
    }
}
