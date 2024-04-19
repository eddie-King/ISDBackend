package com.blanke.hanu.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    double price;
    int quantity;
    @Column(columnDefinition = "TEXT")
    String description;
    String imageUrl;
    String availability;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name ="category_id")
    Category category;
}
