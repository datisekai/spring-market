/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.Models.Category;
import com.example.demo.Models.Vegetable;
import com.example.demo.Services.CategoryService;
import com.example.demo.Services.VegetableService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author datly
 */
@RestController
public class TestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VegetableService vegetableService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Page<Vegetable> index(HttpServletRequest request, Model model) {
        int limit = 8, page = 1;

        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
            }
        }

        String categoryStr = request.getParameter("category");
        String sortStr = request.getParameter("sort");

        Sort sort = Sort.by(Sort.Direction.DESC, "vegetableID");

        if (sortStr != null) {
            if (sortStr.equalsIgnoreCase("low-to-high")) {
                sort = Sort.by(Sort.Direction.ASC, "Price");
            } else if (sortStr.equalsIgnoreCase("high-to-low")) {
                sort = Sort.by(Sort.Direction.DESC, "Price");
            }
        }

//        Pageable pageable = PageRequest.of(page, limit);
        Iterable<Category> categories = categoryService.getAll();

        int CatagoryID = 1;
        Iterator<Category> iterator = categories.iterator();
        if (iterator.hasNext()) {
            CatagoryID = iterator.next().getCatagoryID();
        }

        if (categoryStr != null) {
            try {
                CatagoryID = Integer.parseInt(categoryStr);
            } catch (NumberFormatException e) {
            }
        }
        String nameStr = request.getParameter("name");
        Pageable pageable = PageRequest.of(page - 1, limit);
        String name = "";
        if (nameStr != null) {
            try {
                name = nameStr;
            } catch (Exception e) {
            }
        }
        Page<Vegetable> products = vegetableService.getSortPriceProductsByCategory(CatagoryID,name, pageable);

        model.addAttribute("categories", categories);
        return products;
    }
}
