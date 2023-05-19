/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Services;

import com.example.demo.Models.Customers;
import com.example.demo.Repositories.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author datly
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customers findByFullname(String Fullname) {
        return customerRepository.findByFullName(Fullname);
    }

    public Customers checkLogin(String fullName, String password) {
        Customers cus = customerRepository.findByFullName(fullName);
        if (cus != null && cus.getPassword().equalsIgnoreCase(password)) {
            return cus;
        }

        return null;
    }

    public Customers createCustomers(String fullName, String password, String address, String city) {
        Customers cus = new Customers();
        cus.setAddress(address);
        cus.setCity(city);
        cus.setFullName(fullName);
        cus.setPassword(password);
        return customerRepository.save(cus);
    }
}
