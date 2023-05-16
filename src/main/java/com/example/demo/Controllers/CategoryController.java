package com.example.demo.Controllers;

import com.example.demo.Models.Category;
import com.example.demo.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public Iterable<Category> index(){
        Iterable<Category> categories = categoryService.getAll();
        return categories;
    }
}
