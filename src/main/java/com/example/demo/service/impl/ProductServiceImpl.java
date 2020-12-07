package com.example.demo.service.impl;
import com.example.demo.model.product.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> getAll(Pageable paging) {
        return productRepository.findAll(paging);
    }

    @Override
    public Product createOrder(Product order) {
        return null;
    }
}