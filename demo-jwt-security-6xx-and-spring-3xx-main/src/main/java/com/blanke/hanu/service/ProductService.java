package com.blanke.hanu.service;

import com.blanke.hanu.rest.dto.PagingDTOResponse;
import com.blanke.hanu.rest.dto.ProductDTOCreate;
import com.blanke.hanu.rest.dto.ProductDTOFilter;
import com.blanke.hanu.rest.dto.ProductDTOResponse;

import java.util.List;

public interface ProductService  {
    PagingDTOResponse searchProduct(ProductDTOFilter productDTOFilter);

    ProductDTOResponse createProduct(ProductDTOCreate productDTOCreate);

    ProductDTOResponse getProductById(int id);
}
