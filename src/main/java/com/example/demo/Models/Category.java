package com.example.demo.Models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;


@Entity
@Table(name ="Category")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CatagoryID;

    private String Name;

    private String Description;

    public Integer getCatagoryID() {
        return CatagoryID;
    }

    public void setCatagoryID(Integer CatagoryID) {
        this.CatagoryID = CatagoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
