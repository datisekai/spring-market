package com.example.demo.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.context.annotation.Bean;


@Entity
@Table(name ="`Order`")
public class Order{
    @Id
    @Column(name="OrderID")
    private Integer orderId;

    @Column(name="CustomerID")
    private Integer customerId;

    @Column(name="Date")
    private LocalDate date;
    
    @Column(name="Total")
    private Integer total;
    
    @Column(name="Note")
    private String note;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

 
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
   

}
