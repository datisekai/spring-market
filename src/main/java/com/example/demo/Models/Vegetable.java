package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Vegetable")
@Table(name = "Vegetable")
public class Vegetable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vegetableID;

    private String VegetableName;

    private String Unit;

    private Integer Amount;

    private String Image;

    private Double price;

    @Column(name="CatagoryID")
    private Integer catagoryID;

   
    
    public Integer getVegetableID() {
        return vegetableID;
    }

    public void setVegetableID(Integer VegetableID) {
        this.vegetableID = VegetableID;
    }

    public String getVegetableName() {
        return VegetableName;
    }

    public void setVegetableName(String VegetableName) {
        this.VegetableName = VegetableName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer Amount) {
        this.Amount = Amount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double Price) {
        this.price = Price;
    }

    public int getCatagoryID() {
        return catagoryID;
    }

    public void setCatagoryID(Integer CatagoryID) {
        this.catagoryID = CatagoryID;
    }







}
