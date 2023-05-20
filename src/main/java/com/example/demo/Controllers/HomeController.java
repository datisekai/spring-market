package com.example.demo.Controllers;

import com.example.demo.Models.Category;
import com.example.demo.Models.Vegetable;
import com.example.demo.Services.CategoryService;
import com.example.demo.Services.VegetableService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Iterator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VegetableService vegetableService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        int limit = 8, page = 1;
        String flagSort = "all";
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
            }
        }

        String categoryStr = request.getParameter("category");
        String sortStr = request.getParameter("sort");
        String nameStr = request.getParameter("name");
        Sort sort = Sort.by(Sort.Direction.DESC, "VegetableID");

        if (sortStr != null) {
            if (sortStr.equalsIgnoreCase("low-to-high")) {
                sort = Sort.by(Sort.Direction.ASC, "Price");
                flagSort = "price";
            } else if (sortStr.equalsIgnoreCase("high-to-low")) {
                sort = Sort.by(Sort.Direction.DESC, "Price");
                flagSort = "price";
            } else {
                flagSort = "sold";
            }
        }

        Iterable<Category> categories = categoryService.getAll();

        int category = 0;

        if (categoryStr != null) {
            try {
                category = Integer.parseInt(categoryStr);
            } catch (NumberFormatException e) {
            }
        }
        String name = "";
        if (nameStr != null) {
            try {
                name = nameStr;
            } catch (Exception e) {
            }
        }
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<Vegetable> products = vegetableService.getAll(pageable);
        if (category == 0) {// nếu k có category (có search hoặc k gì cũng được)
                //kiểm tra thành công
                products = vegetableService.getProductsByName(name, pageable);
            if ("sold".equals(flagSort)) {//nếu không có category mà có sort bán chạy
                //kiểm tra thành công
                products = vegetableService.getProductsByBestSelling(name, pageable);
            } else if ("price".equals(flagSort)) {//nếu không có category mà có sort giá
                //kiểm tra thành công
                pageable = PageRequest.of(page - 1, limit, sort);
                products = vegetableService.getProductsByName( name, pageable);
            }
        } else {// nếu có category (có search hoặc k gì cũng được)
                //kiểm tra thành công
                products = vegetableService.getProductsByCategory(category, name, pageable);
            if ("sold".equals(flagSort)) {//nếu có category mà có sort bán chạy
                //kiểm tra thành công
                products = vegetableService.getBestSellingProductsByCategory(category, name, pageable);
            } else if ("price".equals(flagSort)) {//nếu không có category mà có sort giá
                //kiểm tra thành công
                pageable = PageRequest.of(page - 1, limit, sort);
                products = vegetableService.getSortPriceProductsByCategory(category, name, pageable);
            }
        }
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("totalElement", products.getTotalElements());
        model.addAttribute("currentPage", page);

        model.addAttribute("categories", categories);
        return "pages/index";
    }
}
