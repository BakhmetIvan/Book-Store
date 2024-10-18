package com.library.mapper;

import com.library.config.MapperConfig;
import com.library.dto.book.BookDtoWithoutCategoryIds;
import com.library.dto.book.BookResponseDto;
import com.library.dto.book.CreateBookRequestDto;
import com.library.model.Book;
import com.library.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto bookDto, Book book) {
        Set<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoryIds(categoryIds);
    }

    @AfterMapping
    default void setCategories(@MappingTarget Book book, CreateBookRequestDto requestDto) {
        Set<Category> categories = requestDto.getCategoryIds().stream()
                .map(id -> Category.builder().id(id).build())
                .collect(Collectors.toSet());
        book.setCategories(categories);
    }

    void updateBookFromDto(CreateBookRequestDto bookRequestDto, @MappingTarget Book book);
}
