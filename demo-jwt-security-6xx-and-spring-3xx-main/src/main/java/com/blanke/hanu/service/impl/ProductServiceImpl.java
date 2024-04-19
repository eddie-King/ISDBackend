package com.blanke.hanu.service.impl;

import com.blanke.hanu.config.enums.ApiResponseCode;
import com.blanke.hanu.config.exception.RestException;
import com.blanke.hanu.entity.Product;
import com.blanke.hanu.mapper.ProductMapper;
import com.blanke.hanu.repository.ProductRepository;
import com.blanke.hanu.repository.criteria.ProductRepositoryCriteria;
import com.blanke.hanu.rest.dto.ProductDTOCreate;
import com.blanke.hanu.rest.dto.ProductDTOFilter;
import com.blanke.hanu.rest.dto.ProductDTOResponse;
import com.blanke.hanu.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.swing.text.html.Option;
import java.util.List;

@Service
@RequiredArgsConstructor
@ControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepositoryCriteria productRepositoryCriteria;
    ProductRepository productRepository;
    @Override
    public List<ProductDTOResponse> searchProduct(ProductDTOFilter productDTOFilter) {
        return productRepositoryCriteria.search(productDTOFilter);
//        return productRepositoryCriteria.findAll().stream()
//                .map(ProductMapper::toProductDTOResponse)
//                .collect(Collectors.toList());
    }

    @Override
    public ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate) {
        Product product = ProductMapper.toProduct(productDTOCreate);
        product = productRepository.save(product);
        return ProductMapper.toProductDTOResponse(product);
    }

    @Override
    public ProductDTOResponse getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RestException(ApiResponseCode.NOT_FOUND)
        );
        return ProductMapper.toProductDTOResponse(product);
    }


}
