package com.poly.controller;

import com.poly.dao.ProductDAO;
import com.poly.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productDAO.findAll());
        return "product/index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
        productDAO.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Optional<Product> product = productDAO.findById(id);
        product.ifPresent(value -> model.addAttribute("product", value));
        model.addAttribute("products", productDAO.findAll());
        return "product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        productDAO.deleteById(id);
        return "redirect:/admin/products";
    }
}
