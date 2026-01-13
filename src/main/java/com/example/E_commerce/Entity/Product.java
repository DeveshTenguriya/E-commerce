package com.example.E_commerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table
@Setter
@Getter
public class Product extends BaseEntity{
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
