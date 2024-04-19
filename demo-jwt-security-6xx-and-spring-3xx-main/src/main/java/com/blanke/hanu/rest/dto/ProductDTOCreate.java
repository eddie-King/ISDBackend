package com.blanke.hanu.rest.dto;

import com.blanke.hanu.entity.Category;
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
public class ProductDTOCreate {
    String name;
    double price;
    int quantity;
    @Column(columnDefinition = "TEXT")
    String description;
    String imageUrl;
    String availability;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name ="categoryId")
    Category category;
}
