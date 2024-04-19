package com.blanke.hanu.mapper;

import com.blanke.hanu.config.enums.ApiResponseCode;
import com.blanke.hanu.entity.Category;
import com.blanke.hanu.rest.dto.CategoryDTOCreate;
import com.blanke.hanu.rest.dto.CategoryDTOResponse;

public class CategoryMapper {
    public static Category toCategory(CategoryDTOCreate categoryDTOCreate){
        return Category.builder()
                .categoryName(categoryDTOCreate.getCategoryName())
                .build();
    }

    public static CategoryDTOResponse toCategoryDTOResponse(Category category) {
        return CategoryDTOResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
