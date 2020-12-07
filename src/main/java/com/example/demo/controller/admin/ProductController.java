package com.example.demo.controller.admin;

import com.example.demo.model.product.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController(value = "adminProductController")
@RequestMapping("/admin/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping
    public Product updateOrder(@Valid @RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(id, product);
    }
}
