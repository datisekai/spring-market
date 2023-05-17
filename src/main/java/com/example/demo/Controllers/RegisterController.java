/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Customer;
import com.example.demo.Repositories.CustomerRepository;

/**
 *
 * @author datly
 */
@Controller
public class RegisterController {
     @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String index(){
        return "pages/register";
    }
     @RequestMapping(value = "/register/create-account", method = RequestMethod.POST)
     public ResponseEntity<?> registerUser(@RequestBody Customer customer) {
//    	 CustomerRepository customerRepo = new CustomerRepository();
//         if (userRepository.findByUsername(user.getUsername()) != null) {
//             return ResponseEntity.badRequest().body("Username is already taken");
//         }
//
//         userRepository.save(user);
//    	 if(customerRepo)
         return ResponseEntity.ok("User registered successfully");
     }
}
