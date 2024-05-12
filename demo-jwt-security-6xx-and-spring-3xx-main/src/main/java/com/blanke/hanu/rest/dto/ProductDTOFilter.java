package com.blanke.hanu.rest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTOFilter {
    Integer pageIndex = 1;
    Integer pageSize = 9; //default_page_size
    Integer categoryId;
    Double priceFrom = 0d;
    Double priceTo = Double.MAX_VALUE;
    String sortByPrice;
    String name = "";
}
