/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.Models.Vegetable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author datly
 */
@Controller
public class CartController {

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {

        if (session.getAttribute("fullName") == null) {
            return "redirect:/login";
        }

        ArrayList<Vegetable> products = new ArrayList<>();
        ArrayList<Vegetable> tempSession = (ArrayList<Vegetable>) session.getAttribute("carts");
        if (tempSession != null) {
            products = tempSession;
        }
        model.addAttribute("carts", products);

        return "pages/cart";
    }

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public ResponseEntity<String> addToCart(@RequestBody Vegetable vegetable, HttpSession session) {
        ArrayList<Vegetable> products = new ArrayList<>();
        ArrayList<Vegetable> tempSession = (ArrayList<Vegetable>) session.getAttribute("carts");
        if (tempSession != null) {
            products = tempSession;
        }
        boolean check = false;

        if (products.size() >= 0) {
            for (Vegetable v : products) {
                if (v.getVegetableID() == vegetable.getVegetableID()) {
                    v.setAmount(v.getAmount() + 1);
                    check = true;
                    break;
                }

            }

        }
        if (!check) {
            vegetable.setAmount(1);
            products.add(vegetable);
        }
      

        session.setAttribute("carts", products);
        // Thêm sản phẩm vào giỏ hàng
        // Trả về phản hồi thành công
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/cart/edit", method = RequestMethod.GET)
    public ResponseEntity<?> editCart(HttpSession session, HttpServletRequest req) {
        String id = req.getParameter("id");
        String action = req.getParameter("action");

        if (id.trim().length() == 0 || action.trim().length() == 0) {
            return ResponseEntity.noContent().build();
        }

        ArrayList<Vegetable> products = new ArrayList<>();
        ArrayList<Vegetable> tempSession = (ArrayList<Vegetable>) session.getAttribute("carts");
        if (tempSession != null) {
            products = tempSession;
        }
        

        for (Vegetable v : products) {
            if (v.getVegetableID() == Integer.parseInt(id)) {
                switch (action) {
                    case "increase":
                        v.setAmount(v.getAmount() + 1);
                        break;
                    case "decrease":
                        if (v.getAmount() == 1) {
                            products.remove(v);
                        } else {
                            v.setAmount(v.getAmount() - 1);
                        }

                        break;
                }

                break;
            }
        }
        
        
        int sum = 0;
        for (Vegetable v : products) {
            sum += v.getPrice() * v.getAmount();
        }

        session.setAttribute("carts", products);

        
        
        return ResponseEntity.ok().body(sum);

    }
    
}
