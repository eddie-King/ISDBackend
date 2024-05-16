package com.blanke.hanu.rest;

import com.blanke.hanu.rest.dto.*;
import com.blanke.hanu.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/products")
public class ProductController {
    final ProductService productService;
    @GetMapping("/list")
    public PagingDTOResponse searchProduct(@ModelAttribute ProductDTOFilter productDTOFilter){
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
