package com.poly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products") // Ánh xạ với bảng products trong CSDL
public class Product {
    @Id
    private String id;
    private String namesv;
    private BigDecimal price;
    private int quantity;
}
