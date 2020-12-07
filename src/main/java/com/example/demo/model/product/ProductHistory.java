package com.example.demo.model.product;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
@Table(name = "product_history")
@NoArgsConstructor
public class ProductHistory extends AbstractGeneratedIdAuditableTime {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    public ProductHistory(Product product) {
        this.code = product.getCode();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
