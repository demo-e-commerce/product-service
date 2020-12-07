package com.example.demo.controller;

import com.example.demo.controller.dto.RequestPagingDto;
import com.example.demo.model.product.Product;
import com.example.demo.security.User;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public Page<Product> getAll(RequestPagingDto pagingDto) {
        return productService.getAll(pagingDto.getPageable());
    }

    @PostMapping
    public Product createOrder(@Valid @RequestBody Product product, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return productService.createOrder(product);
    }

}
