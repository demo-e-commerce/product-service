package com.example.demo.controller;

import com.example.demo.model.product.Product;
import com.example.demo.service.ProductService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("search")
    public Page<Product> search(@QuerydslPredicate(root = Product.class) Predicate predicate,
                                @RequestParam(value = "sort", defaultValue = "createdDate") String sort,
                                @SortDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.findAll(predicate, pageable);
    }
}
