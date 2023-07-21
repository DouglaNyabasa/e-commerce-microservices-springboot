package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private Long id;
    private String ProductName;
    private String description;
    private int Quantity;
    private double Price;
    private String image;

    private boolean activated;
    private boolean deleted;
    private LocalDate createdOn = LocalDate.now();
    private LocalDate updatedOn =LocalDate.now();
}
