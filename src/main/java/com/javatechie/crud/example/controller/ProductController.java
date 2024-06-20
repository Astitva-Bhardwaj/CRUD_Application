package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> productList = service.getProducts();
        model.addAttribute("products", productList);
        return "index";
    }

    @GetMapping("/addProductForm")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-product";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        service.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/updateProductForm/{id}")
    public String updateProductForm(@PathVariable(value = "id") int id, Model model) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {
        service.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id) {
        service.deleteProduct(id);
        return "redirect:/";
    }
}
