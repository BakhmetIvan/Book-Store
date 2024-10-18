package com.library.service;

import com.library.dto.category.CategoryRequestDto;
import com.library.dto.category.CategoryResponseDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto categoryDto);

    CategoryResponseDto update(Long id, CategoryRequestDto categoryDto);

    void deleteById(Long id);
}
