package com.blanke.hanu.rest.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTOResponse {
    private int id;
    String name;
    double price;
    int quantity;
    @Column(columnDefinition = "TEXT")
    String description;
    String imageUrl;
    String availability;
    @ManyToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name ="categoryId")
    CategoryDTOResponse category;
}

