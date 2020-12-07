package com.example.demo.repository;

import com.example.demo.model.product.Product;
import com.example.demo.model.product.ProductHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductHistoryRepository extends PagingAndSortingRepository<ProductHistory, Long> {
}
