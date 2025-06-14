package org.acme.product.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Product")
public class Product extends PanacheEntity {

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    public String name;

    @Column(length = 1000)
    public String description;

    @NotNull(message = "Product price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Column(nullable = false)
    public Double price;

    @NotNull(message = "Product quantity is required")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    @Column(nullable = false)
    public Integer quantity;

    // Default constructor required by JPA
    public Product() {
    }

    // Constructor with all fields
    public Product(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}