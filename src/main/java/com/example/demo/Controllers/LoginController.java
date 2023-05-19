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
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(HttpSession session) {
        if (session.getAttribute("fullName") != null) {
            return "redirect:/";
        }
        return "pages/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("fullname") String fullname,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
        if (fullname.trim().length() == 0 || password.trim().length() == 0) {
            model.addAttribute("error", "Fullname and password must be required");
            return "pages/login";
        }

        Customers check = customerService.checkLogin(fullname, password);
        if (check != null) {
            // Successful login
            session.setAttribute("fullName", check.getFullName());
            session.setAttribute("customerID", check.getCustomerID());

            return "redirect:/";
        } else {
            // Failed login
            model.addAttribute("error", "Invalid username or password");
            return "pages/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("fullName");
        session.removeAttribute("customerID");

        session.removeAttribute("carts");

        return "redirect:/login";
    }
}
