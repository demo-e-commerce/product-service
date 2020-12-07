package com.example.demo.service.impl;

import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @TestConfiguration
    static class ProductServiceImplTestConfig {
        @Bean
        ProductService orderService() {
            return new ProductServiceImpl();
        }
    }

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

}
