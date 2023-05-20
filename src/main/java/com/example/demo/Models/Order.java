/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author haidu
 */
@Data
@Entity
@Table(name = "`Order`")
public class Order {

    @Id
    @Column(name="OrderID")
    private Integer OrderID;

    private Integer CustomerID;
    
    private Date Date;
    
    private float Total;
    
    private String Note;
    
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
