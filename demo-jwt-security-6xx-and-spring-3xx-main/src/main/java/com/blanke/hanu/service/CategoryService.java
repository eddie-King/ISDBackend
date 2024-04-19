package com.blanke.hanu.service;

import com.blanke.hanu.rest.dto.CategoryDTOCreate;
import com.blanke.hanu.rest.dto.CategoryDTOResponse;

import java.util.List;

public interface CategoryService {
    CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate);

    List<CategoryDTOResponse> getAllCategory();

    CategoryDTOResponse getCategoryById(int id);

    CategoryDTOResponse updateCategoryById(int id);
}
