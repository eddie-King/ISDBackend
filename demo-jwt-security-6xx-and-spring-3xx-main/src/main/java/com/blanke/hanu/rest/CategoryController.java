package com.blanke.hanu.rest;

import com.blanke.hanu.rest.dto.CategoryDTOCreate;
import com.blanke.hanu.rest.dto.CategoryDTOResponse;
import com.blanke.hanu.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/auth/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public CategoryDTOResponse categoryDTOResponse(@RequestBody CategoryDTOCreate categoryDTOCreate){
        return categoryService.createCategory(categoryDTOCreate);
    }

    @GetMapping("/list")
    public List<CategoryDTOResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/{id}")
    public CategoryDTOResponse getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/update/{id}")
    public CategoryDTOResponse updateCategoryById(@PathVariable int id){
        return categoryService.updateCategoryById(id);
    }
}
