package com.blanke.hanu.rest.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductInCartDTO {
    int productId;
    Double price;
    int quantity;
}
