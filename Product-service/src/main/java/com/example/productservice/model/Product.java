package com.example.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products_table")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String productName;
    private String description;
    private int Quantity;
    private Double Price;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private boolean is_activated;
    private boolean is_deleted;
    @CreatedDate
    @JsonIgnore
    private LocalDate createdOn = LocalDate.now();
    @LastModifiedDate
    @JsonIgnore
    private LocalDate updatedOn =LocalDate.now();

    public Product(Long id, String productName, String description, int quantity, double price, String image, boolean is_activated, boolean is_deleted, LocalDate createdOn, LocalDate updatedOn) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        Quantity = quantity;
        Price = price;
        this.image = image;
        this.is_activated = is_activated;
        this.is_deleted = is_deleted;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", Quantity=" + Quantity +
                ", Price=" + Price +
                ", image='" + image + '\'' +
                ", is_activated=" + is_activated +
                ", is_deleted=" + is_deleted +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
