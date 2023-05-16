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

        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
            }
        }

        String categoryStr = request.getParameter("category");
        String sortStr = request.getParameter("sort");

        Sort sort = Sort.by(Sort.Direction.DESC, "VegetableID");

        if (sortStr != null) {
            if (sortStr.equalsIgnoreCase("low-to-high")) {
                sort = Sort.by(Sort.Direction.ASC, "Price");
            } else if (sortStr.equalsIgnoreCase("high-to-low")) {
                sort = Sort.by(Sort.Direction.DESC, "Price");
            } else {
                sort = Sort.by(Sort.Direction.DESC, "totalSold");
            }
        }

        Iterable<Category> categories = categoryService.getAll();

        int category = 1;
        Iterator<Category> iterator = categories.iterator();
        if (iterator.hasNext()) {
            category = iterator.next().getCatagoryID();
        }

        if (categoryStr != null) {
            try {
                category = Integer.parseInt(categoryStr);
            } catch (NumberFormatException e) {
            }
        }

        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Vegetable> products = vegetableService.getAll(pageable);

        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("totalElement", products.getTotalElements());
        model.addAttribute("currentPage",page);

        model.addAttribute("categories", categories);
        return "pages/index";
    }
}
