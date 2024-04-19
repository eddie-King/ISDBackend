package com.blanke.hanu.service.impl;

import com.blanke.hanu.config.enums.ApiResponseCode;
import com.blanke.hanu.config.exception.RestException;
import com.blanke.hanu.entity.Category;
import com.blanke.hanu.mapper.CategoryMapper;
import com.blanke.hanu.repository.CategoryRepository;
import com.blanke.hanu.rest.dto.CategoryDTOCreate;
import com.blanke.hanu.rest.dto.CategoryDTOResponse;
import com.blanke.hanu.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    @Override
    public CategoryDTOResponse createCategory(CategoryDTOCreate categoryDTOCreate) {
        Category category = CategoryMapper.toCategory(categoryDTOCreate);
        category = categoryRepository.save(category);

        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public List<CategoryDTOResponse> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTOResponse getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RestException(ApiResponseCode.NOT_FOUND)
        );
        return CategoryMapper.toCategoryDTOResponse(category);
    }

    @Override
    public CategoryDTOResponse updateCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new RestException(ApiResponseCode.NOT_FOUND)
        );
        return null;
    }
}
