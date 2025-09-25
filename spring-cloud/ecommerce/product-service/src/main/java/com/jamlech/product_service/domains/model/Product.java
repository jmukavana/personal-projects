package com.jamlech.product_service.domains.model;

import com.jamlech.product_service.domains.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product extends BaseEntity {
   @Column(name = "product_name",nullable = false)
    private String productName;

   @Column(name = "production_description")
    private String productDescription;

   @Column(name = "price",nullable = false)
    private BigDecimal price;

   @Column(name= "discount_percentage")
    private Integer discountPercentage;
}
