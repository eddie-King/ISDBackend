package com.blanke.hanu.rest;

import com.blanke.hanu.rest.dto.CategoryDTOResponse;
import com.blanke.hanu.rest.dto.ProductDTOCreate;
import com.blanke.hanu.rest.dto.ProductDTOFilter;
import com.blanke.hanu.rest.dto.ProductDTOResponse;
import com.blanke.hanu.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/auth/products")
public class ProductController {
    final ProductService productService;
    @GetMapping("/list")
    public List<ProductDTOResponse> searchProduct(@ModelAttribute ProductDTOFilter productDTOFilter){
        return productService.searchProduct(productDTOFilter);
    }
    @PostMapping("/add")
    public ProductDTOResponse productDTOResponse(@RequestBody ProductDTOCreate productDTOCreate){
        return productService.createProduct(productDTOCreate);
    }

    @GetMapping("/{id}")
    public ProductDTOResponse getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }


}
