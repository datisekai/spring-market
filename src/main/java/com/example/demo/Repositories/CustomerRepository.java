/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Repositories;

import com.example.demo.Models.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author datly
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customers, Object> {
    Customers findByFullName(String fullName);
}
