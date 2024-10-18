package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.category.CategoryRequestDto;
import com.library.dto.category.CategoryResponseDto;
import com.library.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toModel(CategoryRequestDto categoryDTO);

    void updateCategoryFromDto(CategoryRequestDto categoryDto, @MappingTarget Category category);
}
