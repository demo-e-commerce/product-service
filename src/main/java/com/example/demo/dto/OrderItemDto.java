package com.example.demo.dto;

import com.example.demo.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter @Setter
@NoArgsConstructor
public class OrderItemDto {

    @NotBlank(message = "product code is mandatory")
    private String code;

    @NotNull(message = "quantity is mandatory")
    @Positive(message = "quantity must greater than zero")
    private Integer quantity;

    private String title;

    private String description;

    private Float price;

    public OrderItemDto(Product product) {
        this.code = product.getCode();
        this.quantity = product.getQuantity();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
