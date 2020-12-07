package com.example.demo.controller.internal;

import com.example.demo.controller.exception.InvalidOrderException;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.model.product.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/internal/api/v1")
public class OrderController {

    @Autowired
    ProductService productService;

    @PostMapping("process-order")
    public List<OrderItemDto> processOrder(@RequestBody @Valid List<OrderItemDto> orderItemDtos) throws InvalidOrderException {
        return productService.processOrder(orderItemDtos);
    }
}
