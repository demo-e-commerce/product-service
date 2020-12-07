package com.example.demo.model.product;

import com.example.demo.model.core.AbstractGeneratedIdAuditableTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "product")
@NoArgsConstructor @AllArgsConstructor
public class Product extends AbstractGeneratedIdAuditableTime {

    @Column(name = "code", unique = true)
    @NotBlank(message = "Order item code is mandatory")
    private String code;

    @Column(name = "title")
    @NotBlank(message = "Order item title is mandatory")
    private String title;

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description != null ? description : title;
    }

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    @NotNull(message = "order item quantity is mandatory")
    private Integer quantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductHistory> productHistories;

}
