/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.Models.Customers;
import com.example.demo.Services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String index(HttpSession session) {
        if (session.getAttribute("fullName") != null) {
            return "redirect:/";
        }

        return "pages/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpSession session, @RequestParam("fullname") String fullName, @RequestParam("password") String password, @RequestParam("city") String city, @RequestParam("address") String address, Model model) {
        if (fullName.trim().length() == 0 || password.trim().length() == 0 || city.trim().length() == 0 || address.trim().length() == 0) {
            model.addAttribute("error", "Please enter full field");
            return "pages/register";
        }

        Customers isExist = customerService.findByFullname(fullName);
        System.out.println(isExist);
        if (isExist != null) {
            model.addAttribute("error", "Fullname is already");
            return "pages/register";
        }

        Customers cus = customerService.createCustomers(fullName, password, address, city);

        session.setAttribute("fullName", cus.getFullName());
        session.setAttribute("customerID", cus.getCustomerID());

        return "redirect:/";

    }
}
