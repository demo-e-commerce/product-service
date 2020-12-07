package com.example.demo.service;

import com.example.demo.controller.exception.InvalidOrderException;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.model.product.Product;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAll(Predicate predicate, Pageable pageable);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product order);

    List<OrderItemDto> processOrder(List<OrderItemDto> orderItems) throws InvalidOrderException;
}
