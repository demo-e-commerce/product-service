package com.example.demo.service.impl;
import com.example.demo.controller.exception.NotFoundException;
import com.example.demo.controller.exception.InvalidOrderException;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.model.product.Product;
import com.example.demo.model.product.ProductHistory;
import com.example.demo.repository.ProductHistoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductHistoryRepository productHistoryRepository;

    @Override
    public Page<Product> findAll(Predicate predicate, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        return productRepository.findAll(builder.and(predicate), pageable);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    private void saveProductHistory(Product currentProduct) {
        productHistoryRepository.save(new ProductHistory(currentProduct));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> currentProduct = productRepository.findById(id);
        if (currentProduct.isEmpty()) {
            throw new NotFoundException("product not found");
        }

        Product updatedProduct = currentProduct.get();
        updatedProduct.setCode(product.getCode());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());

        saveProductHistory(currentProduct.get());
        return productRepository.save(product);
    }

    @Override
    public List<OrderItemDto> processOrder(List<OrderItemDto> orderItems) throws InvalidOrderException {
        List<Product> products = getProducts(orderItems);

        List<OrderItemDto> outOfStock = new ArrayList<>();
        List<OrderItemDto> successOrderItems = new ArrayList<>();
        products.forEach(product -> {
            OrderItemDto orderItemDto = orderItems.stream()
                    .filter(orderItem -> orderItem.getCode().equals(product.getCode()))
                    .findFirst().orElse(null);
            if (orderItemDto != null && product.getQuantity() >= orderItemDto.getQuantity()) {
                successOrderItems.add(new OrderItemDto(product));
                product.setQuantity(product.getQuantity() - orderItemDto.getQuantity());
            } else {
                outOfStock.add(orderItemDto);
            }
        });

        validateOrder(orderItems, successOrderItems, outOfStock);

        productRepository.saveAll(products);
        return successOrderItems;
    }

    private void validateOrder(List<OrderItemDto> requestOrderItemDto,
                               List<OrderItemDto> successOrderItems,
                               List<OrderItemDto> failedOrderItems) throws InvalidOrderException {

        List<String> notFound = new ArrayList<>();
        List<String> outOfStock = new ArrayList<>();
        // throw error if there is failed items
        if (!failedOrderItems.isEmpty()) {
            outOfStock = failedOrderItems.stream()
                    .map(OrderItemDto::getCode)
                    .collect(Collectors.toList());
        }

        if (requestOrderItemDto.size() > successOrderItems.size()) {
            notFound = requestOrderItemDto.stream()
                    .map(OrderItemDto::getCode)
                    .collect(Collectors.toList());
            notFound.removeAll(successOrderItems.stream()
                    .map(OrderItemDto::getCode)
                    .collect(Collectors.toList()));
        }

        if (notFound.size() > 0 || outOfStock.size() > 0) {
            throw new InvalidOrderException(notFound, outOfStock);
        }
    }

    private List<Product> getProducts(List<OrderItemDto> orderItems) {
        List<Product> products = productRepository.findAllByCodeInAndPriceIsNotNull(
                orderItems.stream()
                        .map(OrderItemDto::getCode)
                        .collect(Collectors.toList()));
        return products;
    }
}