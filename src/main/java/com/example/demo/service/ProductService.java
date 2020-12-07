package com.example.demo.service;

import com.example.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAll(Pageable paging);

    Product createOrder(Product product);
}
