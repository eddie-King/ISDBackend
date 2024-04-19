package com.blanke.hanu.mapper;

import com.blanke.hanu.entity.Product;
import com.blanke.hanu.rest.dto.PagingDTOResponse;
import com.blanke.hanu.rest.dto.ProductDTOCreate;
import com.blanke.hanu.rest.dto.ProductDTOResponse;

public class ProductMapper {
    public static Product toProduct(ProductDTOCreate productDTOCreate) {
        return Product.builder()
                .name(productDTOCreate.getName())
                .price(productDTOCreate.getPrice())
                .quantity(productDTOCreate.getQuantity())
                .description(productDTOCreate.getDescription())
                .imageUrl(productDTOCreate.getImageUrl())
                .availability(productDTOCreate.getAvailability())
                .category(productDTOCreate.getCategory())
                .build();
    }
    public static ProductDTOResponse toProductDTOResponse(Product product) {

        return ProductDTOResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .availability(product.getAvailability())
                .category(CategoryMapper.toCategoryDTOResponse(product.getCategory()))
                .build();
    }


}
